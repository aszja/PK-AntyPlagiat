package pl.pk.antyplagiat;

import java.util.Date;

import pl.pk.antyplagiat.dao.ThesisDao;
import pl.pk.antyplagiat.dao.ThesisDaoImpl;
import pl.pk.antyplagiat.domain.Thesis;

public class SampleDao {

	public void sampleUsage() {
		ThesisDao thesisDao = new ThesisDaoImpl();
		
		Thesis thesis = new Thesis();
		thesis.setAuthor("some author");
		thesis.setCreationDate(new Date());
		thesis.setDescription(null);
		thesis.setTitle("some title");
		
		thesisDao.save(thesis);
	}
	
}
