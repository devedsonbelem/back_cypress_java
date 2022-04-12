package br.com.dto;

import java.io.Serializable;

import br.com.domain.Users;

public class UsersDto implements Serializable {

		private static final long serialVersionUID = 1L;

		private String id;
		private String name;
		private String email;

	 
		public UsersDto(Users obj) {
			id = obj.getId();
			name = obj.getName();
		    email = obj.getEmail();
		}

		public UsersDto() {
		}
		
	   


		@Override
		public String toString() {
			return "UsersDto [id=" + id + ", name=" + name + ", email=" + email + "]";
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
 }
 
