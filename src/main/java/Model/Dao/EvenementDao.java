package Model.Dao;

import Model.Entity.Evenement;
import Model.Entity.Evenement_Participant;
import Model.Entity.Users;
import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EvenementDao extends Dao<Evenement> {
    public List<Evenement_Participant> getUserEvenement(Users user){

        return  user.getUser_participation();
    }
    public List<Evenement> EvenementNowWithSomeCentreInteret(Users user){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        Query q = s.createQuery("select t from "+ Evenement.class.getSimpleName()+" t where etat = :x and centreInt in :y" );
        q.setParameter("x", Evenement.Etat.Invitation).setParameter("y",user.getCentreInteret());
        List<Evenement> list = q.list();
        s.close();

        return list;
    }
}
