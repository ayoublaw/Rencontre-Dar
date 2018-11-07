package Model.Dao;

import Model.Entity.Users;
import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class UsersDao extends Dao<Users> {

    public Users GetUserByName(String Nom,String prenom){
        String query = "select t from "+Users.class.getSimpleName()+" t where Nom = :x and prenom = :y";
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery(query);
        q.setParameter("x",Nom).setParameter("y",prenom);
        Users user = q.list().size() == 0 ? null : (Users) q.list().get(0);
        session.close();
        return user;
    }

    public Users GetUserByEmail(String Email){
        List<Users> list =selectAll().stream().filter(r ->r.getEmail().equals(Email)).collect(Collectors.toList());
        if(list.isEmpty()){
            return  null;
        }
        return list.get(0);
    }



}

