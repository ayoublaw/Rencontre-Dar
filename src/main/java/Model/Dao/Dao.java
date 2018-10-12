package Model.Dao;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class Dao<E> implements IDao<E> {
    private final Class<E> clazz;
    public Dao() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        clazz = (Class<E>) type.getActualTypeArguments()[0];

    }

    public void Save(E entity) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        s.beginTransaction();
        s.save(entity);
        s.getTransaction().commit();
        s.close();
    }
    public E getById(Serializable id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        s.beginTransaction();
        E entity = (E) s.get(clazz, id);
        s.getTransaction().commit();
        s.close();
        return entity;
    }
    public E update(E entity) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        s.beginTransaction();
        s.update(entity);
        s.getTransaction().commit();
        s.close();
        return null;
    }
}
