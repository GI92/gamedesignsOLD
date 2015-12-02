package com.gamedesigns.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.gamedesigns.validator.UniqueCase;

@Entity
public class Design {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "desgin_ID")
	private Long ID;

	@NotEmpty
	@UniqueCase(className = "Design", columnName = "name")
	private String name;

	private String description;

	@Column(name = "ARCHIVE_NAME")
	private String archiveName;

	@Column(columnDefinition = "LONGBLOB")
	private byte[] sourcecode;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(columnDefinition = "LONGBLOB")
	private byte[] defaultImage;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "design")
	private Set<Preview> images;

	@ManyToMany
	private Set<GameType> gameTypes;

	@ManyToOne
	private Category category;

	@ManyToOne
	private User user;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getSourcecode() {
		return sourcecode;
	}

	public void setSourcecode(byte[] sourcecode) {
		this.sourcecode = sourcecode;
	}

	public byte[] getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(byte[] defaultImage) {
		this.defaultImage = defaultImage;
	}

	public Set<Preview> getImages() {
		return images;
	}

	public void setImages(Set<Preview> images) {
		this.images = images;
	}

	public Set<GameType> getGameTypes() {
		return gameTypes;
	}

	public void setGameTypes(Set<GameType> gameTypes) {
		this.gameTypes = gameTypes;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
