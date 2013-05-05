package pl.pk.antyplagiat.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pl.pk.antyplagiat.dao.util.HibernateUtil;

public class HibernateDao<CLAZZ, KEY extends Serializable> implements GenericDao<CLAZZ, KEY> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Class<? extends CLAZZ> daoType;

    @SuppressWarnings("unchecked")
    public HibernateDao() {
        daoType = (Class<CLAZZ>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void save(CLAZZ entity) {
        getCurrentSession().beginTransaction();
        getCurrentSession().save(entity);
        getCurrentSession().getTransaction().commit();
    }

    public void saveOrUpdate(CLAZZ entity) {
        getCurrentSession().beginTransaction();
        getCurrentSession().saveOrUpdate(entity);
        getCurrentSession().getTransaction().commit();
    }

    public void update(CLAZZ entity) {
        getCurrentSession().beginTransaction();
        getCurrentSession().update(entity);
        getCurrentSession().getTransaction().commit();
    }

    public void delete(CLAZZ entity) {
        getCurrentSession().beginTransaction();
        getCurrentSession().delete(entity);
        getCurrentSession().getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public CLAZZ find(KEY key) {
        getCurrentSession().beginTransaction();
        CLAZZ result = (CLAZZ) getCurrentSession().get(daoType, key);
        getCurrentSession().getTransaction().commit();
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<CLAZZ> list() {
        getCurrentSession().beginTransaction();
        List<CLAZZ> list = getCurrentSession().createCriteria(daoType).list();
        getCurrentSession().getTransaction().commit();
        return list;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
