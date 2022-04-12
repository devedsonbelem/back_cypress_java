package br.com.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.domain.Post;
import br.com.domain.Users;
import br.com.dto.UsersDto;
import br.com.services.UsersService;

@RestController
@RequestMapping(value = "/users")
public class UsersControllers {

	@Autowired
	private UsersService userService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public ResponseEntity<List<UsersDto>> findAll() {
		List<Users> list = userService.findAll();
		List<UsersDto> listDto = list.stream().map(x -> new UsersDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/find/{_id}", method = RequestMethod.GET)
	public ResponseEntity<UsersDto> findById(@PathVariable String _id) {
		Users obj = userService.findById(_id);
		return ResponseEntity.ok().body(new UsersDto(obj));
	}

	@PostMapping(value = "/create")
	public ResponseEntity<?> insert(@RequestBody UsersDto objDto) {
		Users obj = userService.fromDTO(objDto);
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		Object a = ResponseEntity.created(uri).build();
		if (a != null) {
		    Map <String,String> mapa = new HashMap<String,String>();
		    mapa.put("msg", "dados gravados com sucesso");
			return ResponseEntity.status(200).body(mapa);
		} else {
			return ResponseEntity.status(500).body("erro de gravacao");
		}
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UsersDto objDto, @PathVariable String id) {
		Users obj = userService.fromDTO(objDto);
		obj.setId(id);
		obj = userService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/findById/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		Users obj = userService.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}

}