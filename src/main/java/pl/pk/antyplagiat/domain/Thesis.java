package pl.pk.antyplagiat.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "THESIS")
public class Thesis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "AUTHOR", nullable = false)
	private String author;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "DESCROPTION", nullable = true)
	private String description;
	
	@Column(name = "CREATION_DATE", nullable = false)
	private Date creationDate;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "thesis", targetEntity = ThesisFile.class)
	private Set<ThesisFile> thesisFiles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Set<ThesisFile> getThesisFiles() {
		return thesisFiles;
	}

	public void setThesisFiles(Set<ThesisFile> thesisFiles) {
		this.thesisFiles = thesisFiles;
	}
	
}
