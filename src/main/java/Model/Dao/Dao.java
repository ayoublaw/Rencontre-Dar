package Model.Dao;

import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        factory.close();
    }
    public E getById(Serializable id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        s.beginTransaction();
        E entity = (E) s.get(clazz, id);
        s.getTransaction().commit();
        s.close();
        factory.close();
        return entity;
    }
    public E update(E entity) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        s.beginTransaction();
        s.update(entity);
        s.getTransaction().commit();
        s.close();
        factory.close();
        return null;
    }

    @Override
    public E remove(E e) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.getCurrentSession();
        s.beginTransaction();
        s.delete(e);
        s.getTransaction().commit();
        s.close();
        factory.close();
        return e;
    }
    @Override
    public List<E> selectAll() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        Query q = s.createQuery("from " + clazz.getSimpleName());
        List<E> list = q.list();
        s.close();
        factory.close();
        return list;
    }

}
