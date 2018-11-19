package Model.Dao;

import Model.Entity.Address;
import Model.Entity.Users;
import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AddressDao extends Dao<Address> {
    public List<Address> selectUser(Users user) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        Query q = s.createQuery("select t from "+Address.class.getSimpleName()+" t where User_id = :x" );
        q.setParameter("x",user.getId());
        List<Address> list = q.list();
        s.close();
        factory.close();
        return list;
    }
    public Address selectUserOneAddress(Users user,String nom) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        Query q = s.createQuery("select t from "+Address.class.getSimpleName()+" t where User = :x and nom = :y" );
        q.setParameter("x",user.getId()).setParameter("y",nom);
        List<Address> list = q.list();
        s.close();
        factory.close();
        return list.get(0);
    }

}
