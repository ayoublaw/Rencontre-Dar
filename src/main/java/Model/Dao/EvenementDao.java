package Model.Dao;

import Model.Entity.Evenement;
import Model.Entity.Evenement_Participant;
import Model.Entity.Users;

import java.util.List;
import java.util.stream.Collectors;

public class EvenementDao extends Dao<Evenement> {
    public List<Evenement_Participant> getUserEvenement(Users user){

        return  user.getUser_participation();
    }
    public List<Evenement> EvenementNowWithSomeCentreInteret(Users user){
        List<String> list = user.getCentreInteret().stream().map(r -> r.getName()).collect(Collectors.toList());
        return this.selectAll().stream().filter(r->r.getEtat().equals(Evenement.Etat.Invitation)).filter(r -> list.contains(r.getCentreInt().getName())).collect(Collectors.toList());
    }
    public List<Evenement> GetEvenementActifCreateByUser(Users user){
        return user.getEvenement_create().stream().filter(r -> !r.getEtat().equals(Evenement.Etat.Expirer))
                .collect(Collectors.toList());
    }
    public List<Evenement> GetEvenementActifParticipateByUser(Users user){
        return user.getUser_participation().stream().filter(r -> !r.getEvenement().getEtat().equals(Evenement.Etat.Expirer))
                .map(x ->x.getEvenement()).collect(Collectors.toList());
    }
    public List<Evenement> GetEvenementEtatAttendAcceptation(Users user){
        return user.getEvenement_create().stream().filter(r -> r.getEtat().equals(Evenement.Etat.AttendAcceptation)).collect(Collectors.toList());
    }
}
