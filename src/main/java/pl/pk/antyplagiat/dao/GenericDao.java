package pl.pk.antyplagiat.dao;

import java.util.List;

public interface GenericDao<CLAZZ, KEY> {

	void save(CLAZZ entity);

	void saveOrUpdate(CLAZZ entity);

	void update(CLAZZ entity);

	void delete(CLAZZ entity);

	CLAZZ find(KEY key);

	List<CLAZZ> list();
}
