package Model.Dao;

import Model.Entity.CentreInt;
import Model.Entity.Users;
import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CentreIntDao  extends Dao<CentreInt>{
    public CentreInt getbynameanduser(String  name, Users user){
        String query = "select t from "+CentreInt.class.getSimpleName()+" t where Name = :x and users_id = :y";
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery(query);
        q.setParameter("x",name).setParameter("y",user.getId());
        CentreInt centreint = (CentreInt) q.list().get(0);
        session.close();
        factory.close();
        return centreint;

    }
}
