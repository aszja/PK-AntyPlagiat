package pl.pk.antyplagiat.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "THESIS_FILES")
public class ThesisFile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Thesis.class)
	@ForeignKey(name = "FK_THESIS")
	@JoinColumn(name = "THESIS_ID", referencedColumnName = "ID", nullable = false)
	private Thesis thesis;
	
	@Column(name = "FILE_PATH", nullable = false)
	private String filePath;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "thesisFile", targetEntity = ThesisFileContent.class)
	private Set<ThesisFileContent> thesisFile;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Thesis getThesis() {
		return thesis;
	}

	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Set<ThesisFileContent> getThesisFile() {
		return thesisFile;
	}

	public void setThesisFile(Set<ThesisFileContent> thesisFile) {
		this.thesisFile = thesisFile;
	}
	
}
