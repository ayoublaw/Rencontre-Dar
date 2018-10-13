package Model.Dao;

import java.io.Serializable;

public interface IDao<E> {
    void Save(E e);
    E getById(Serializable id);
    E update(E e);
    E remove(E e);
    }
