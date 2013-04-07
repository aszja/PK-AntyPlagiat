package pl.pk.antyplagiat.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "THESIS_FILE_CONTENT")
public class ThesisFileContent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ThesisFile.class)
	@ForeignKey(name = "FK_THESIS_FILE")
	@JoinColumn(name = "THESIS_FILE_ID", referencedColumnName = "ID", nullable = false)
	private ThesisFile thesisFile;
	
	@Column(name = "PARAGRAPH", nullable = false)
	private String paragraph;
	
	@Column(name = "WORDS_FOUND", nullable = false)
	private String wordsFound;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ThesisFile getThesisFile() {
		return thesisFile;
	}

	public void setThesisFile(ThesisFile thesisFile) {
		this.thesisFile = thesisFile;
	}

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	public String getWordsFound() {
		return wordsFound;
	}

	public void setWordsFound(String wordsFound) {
		this.wordsFound = wordsFound;
	}
	
}
