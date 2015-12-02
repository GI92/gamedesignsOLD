package com.gamedesigns.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.gamedesigns.validator.UniqueCase;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@NotEmpty
	@UniqueCase(className = "Category", columnName = "name")
	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private Set<Design> designs;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Design> getDesigns() {
		return designs;
	}

	public void setDesigns(Set<Design> designs) {
		this.designs = designs;
	}

}
