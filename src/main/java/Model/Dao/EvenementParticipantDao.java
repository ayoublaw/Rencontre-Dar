package Model.Dao;

import Model.Entity.Evenement;
import Model.Entity.Evenement_Participant;
import Model.Entity.Users;
import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class EvenementParticipantDao extends Dao<Evenement_Participant> {
    public List<Evenement_Participant> getEvenementParticipantFromEvenement(Evenement ev){
        return this.selectAll().stream().filter(r ->r.getEvenement().getId() == ev.getId()).collect(Collectors.toList());
    }
    public Evenement_Participant getEvenementParticipantFromEvenementAndUser(Evenement ev,Users user){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        Query q = s.createQuery("select t from " + Evenement_Participant.class.getSimpleName()+" t where evenemrnt = :x and user = :y");
        q.setParameter("x",ev.getId()).setParameter("y",user.getId());
        Evenement_Participant list = (Evenement_Participant) q.list().get(0);
        s.close();
        factory.close();
        return list;
    }
}
