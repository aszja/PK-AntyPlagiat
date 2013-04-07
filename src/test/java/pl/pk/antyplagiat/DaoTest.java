package pl.pk.antyplagiat;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import pl.pk.antyplagiat.dao.util.HibernateUtil;

public class DaoTest extends TestCase {

	@Test
	public void testSessonFactory() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		assertNotNull(sessionFactory);
	}
	
	@Test
	public void testSession() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		assertNotNull(session);
	}
}
