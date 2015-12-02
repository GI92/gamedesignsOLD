package com.gamedesigns.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.gamedesigns.validator.UniqueCase;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@NotEmpty
	@UniqueCase(className = "User", columnName = "username")
	@Column(unique = true)
	private String username;

	@NotEmpty
	@Size(min = 6, max = 15)
	private String password;

	@NotEmpty
	@Email
	@UniqueCase(className = "User", columnName = "email")
	@Column(unique = true)
	private String email;

	private boolean administrator;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	private Set<Design> designs;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public Set<Design> getDesigns() {
		return designs;
	}

	public void setDesigns(Set<Design> designs) {
		this.designs = designs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
