package com.blog.api.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.blog.api.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Integer id; 
	
	@NotEmpty
	@Size(min = 3, message = "Username must be minimum of 3 characters !!" )
	private String name;
	@Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$" ,message = "Email address is not valid !!")
	private String email;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be minimum of 3 character and maximum of 10 character")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	

}
