package Model.Dao;

import Model.Entity.Address;
import Model.Entity.Users;
import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UsersDao extends Dao<Users> {

    public Users GetUserByName(String Nom,String prenom){
        String query = "select t from "+Users.class.getSimpleName()+" t where Nom = :x and prenom = :y";
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery(query);
        q.setParameter("x",Nom).setParameter("y",prenom);
        Users user = q.list().size() == 0 ? null : (Users) q.list().get(0);
        return user;
    }

    public Users GetUserByEmail(String Email){
        String query = "select t from "+Users.class.getSimpleName()+" t where email = :x ";
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery(query);
        q.setParameter("x",Email);
        Users user = q.list().size() == 0 ? null : (Users) q.list().get(0);
        return user;
    }



}

