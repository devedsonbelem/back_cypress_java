package br.com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.Users;
import br.com.dto.UsersDto;
import br.com.exception.ObjectNotFoundException;
import br.com.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository userRepository;

	public List<Users> findAll() {
		return userRepository.findAll();
	}

	public Users findById(String id) {
		Optional<Users> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}

	public Users insert(Users obj) {
		return userRepository.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public Users update(Users obj) {
		Users newObj = findById(obj.getId());
		updateData(newObj, obj);
		return userRepository.save(newObj);
	}

	private void updateData(Users newObj, Users obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public Users fromDTO(UsersDto objDto) {
		return new Users(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}

