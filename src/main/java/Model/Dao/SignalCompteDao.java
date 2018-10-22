package Model.Dao;

import Model.Entity.SignalCompte;
import Model.Entity.Users;
import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class SignalCompteDao extends Dao<SignalCompte> {
    public List<SignalCompte> getSignalCompteForoneUser(Users user){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        Query q = s.createQuery("select t from " + SignalCompte.class.getSimpleName()+" t where User_signal = :x");
        q.setParameter("x",user.getId());
        List<SignalCompte> list = q.list();
        s.close();
        return list;

    }
}
