package Controlleur.Service;

import Controlleur.Exception.DataException;
import Model.Dao.DaoFactory;
import Model.Entity.CentreInt;
import Model.Entity.Evenement;
import Model.Entity.Evenement_Participant;
import Model.Entity.Users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class EvenementService {
    public Evenement AddEvenement(Users user,String adr_proposer, String description, String Lieu, String nbrParticipants, String date, String centreint) throws ParseException, DataException {
        if(Integer.parseInt(nbrParticipants) != 2 && Lieu == null ){
            throw new DataException("Vous avez pas choisir un lieu");
        }
        if(Integer.parseInt(nbrParticipants) == 2 && adr_proposer == null){
            throw new DataException("Vous avez pas proposé une addresse");
        }
        if(adr_proposer == null || description == null || date == null || centreint == null){
            throw new DataException("Remplir tous les champs");
        }
        Evenement e = new Evenement();
        CentreInt c = DaoFactory.getCenterIntDao().getbynameanduser(centreint,user);
        if(c == null){
            throw new DataException("Centre Interet not found");
        }
        e.setDescription(description);
        e.setEtat(Evenement.Etat.Invitation);
        e.setLieu(Lieu);
        e.setAdr_Proposer(adr_proposer);
        e.setNbrParticipant(Integer.parseInt(nbrParticipants));
        e.setUser_create(user);
        e.setDate(new SimpleDateFormat("yyyy-MM-DD'T'HH:mm").parse(date));
        e.setCentreInt(c);
        DaoFactory.getEvenementDao().Save(e);
        return e;
    }
    public List<Evenement> ListEvenementCanParticipate(Users user) throws DataException {
        if(user == null){
            throw new DataException("User not found");
        }
        List<Evenement>  listEvenements = DaoFactory.getEvenementDao().EvenementNowWithSomeCentreInteret(user);
        if(listEvenements == null || listEvenements.isEmpty()){
            throw new DataException("On a pas de propositions d'evenement");
        }
        System.out.println("Email :" +listEvenements.get(0).getUser_create().getEmail());
        return listEvenements;
    }
    public Evenement_Participant ParticipateInEvenement(Users user,int EvenementId,String lieu) throws DataException {
        Evenement evenement = DaoFactory.getEvenementDao().getById(EvenementId);
        if(evenement == null){
            throw new DataException("Evenement n'existe pas");
        }
        if(evenement.getEtat() != Evenement.Etat.Invitation){
            throw new DataException("Vous avez pas le droit de participer");
        }
        if(evenement.getNbrParticipant() == 2 && lieu == null){
            throw new DataException("vous avez pas choisir un lieu");
        }
        if(evenement.getNbrParticipant() == evenement.getUsers_participate().size() +1){
            throw new DataException("Y'a plus de place ");
        }
        Evenement_Participant evenement_participant = new Evenement_Participant();
        evenement_participant.setEvenement(evenement);
        evenement_participant.setUser_participate(user);
        DaoFactory.getEvenementParticipantDao().Save(evenement_participant);

        user.getUser_participation().add(evenement_participant);
        DaoFactory.getUsersDao().update(user);

        evenement.getUsers_participate().add(evenement_participant);
        if(evenement.getNbrParticipant() == evenement.getUsers_participate().size()+1){
            if(evenement.getNbrParticipant() == 2){
                evenement.setEtat(Evenement.Etat.AttendAcceptation);
                evenement.setLieu(lieu);
            }
            else{
                evenement.setEtat(Evenement.Etat.Complet);
            }
        }
        DaoFactory.getEvenementDao().update(evenement);
        return evenement_participant;
    }
    public List<Evenement> ListEvenementCreateActif(Users user) throws DataException {
        List<Evenement> listevenements = DaoFactory.getEvenementDao().GetEvenementActifCreateByUser(user);
        if(listevenements == null || listevenements.isEmpty()){
            throw new DataException("Vous avez pas d'evenement");
        }
        return listevenements;
    }
    public List<Evenement> ListEvenementCreate(Users user) throws DataException {
        List<Evenement> listevenements = user.getEvenement_create();
        if(listevenements == null || listevenements.isEmpty()){
            throw new DataException("Vous avez pas d'evenement");
        }
        return listevenements;
    }
    public List<Evenement> ListEvenementParticipateActif(Users user) throws DataException {
        List<Evenement> listevenements = DaoFactory.getEvenementDao().GetEvenementActifParticipateByUser(user);
        if(listevenements == null || listevenements.isEmpty()){
            throw new DataException("Vous avez pas de participations");
        }
        return listevenements;
    }
    public List<Evenement> ListEvenementParticipate(Users user) throws DataException {
        List<Evenement> listevenements = user.getUser_participation().stream().map(r -> r.getEvenement()).collect(Collectors.toList());
        if(listevenements == null || listevenements.isEmpty()){
            throw new DataException("Vous avez pas de participation");
        }
        return listevenements;

    }
    public void DeleteEvenement(Users user,int Evenement) throws DataException {
        Evenement ev = DaoFactory.getEvenementDao().getById(Evenement);
        if(ev == null){
            throw new DataException("Evenement don't exist");
        }
        if(ev.getUser_create().getId() != user.getId()){
            throw  new DataException("you don't have permission");
        }
        List<Evenement_Participant> evenement_participants = DaoFactory.getEvenementParticipantDao().getEvenementParticipantFromEvenement(ev);
        if(evenement_participants != null && !evenement_participants.isEmpty()){
            for(Evenement_Participant e : evenement_participants){
            DaoFactory.getEvenementParticipantDao().remove(e);
            Users user_participate = e.getUser_participate();
            user_participate.getUser_participation().remove(e);
            DaoFactory.getUsersDao().update(user_participate);
            }
        }
        DaoFactory.getEvenementDao().remove(ev);
        user.getEvenement_create().remove(ev);
        DaoFactory.getUsersDao().update(user);
    }
    public void DeleteEvenementParticipate(Users user,int Evenement) throws DataException {
        Evenement ev = DaoFactory.getEvenementDao().getById(Evenement);
        if(ev == null){
            throw new DataException("Evenement don't exist");
        }
        if(!ev.getUsers_participate().contains(user)){
            throw  new DataException("you don't have permission");
        }
        Evenement_Participant evenement_participants = DaoFactory.getEvenementParticipantDao().getEvenementParticipantFromEvenementAndUser(ev,user);
        if(evenement_participants != null){
            DaoFactory.getEvenementParticipantDao().remove(evenement_participants);
            ev.getUsers_participate().remove(evenement_participants);
            DaoFactory.getEvenementDao().update(ev);
            user.getUser_participation().remove(evenement_participants);
            DaoFactory.getUsersDao().update(user);
        }
        else {
            Evenement e = (Model.Entity.Evenement) user.getUser_participation().stream().map(r -> r.getEvenement())
                    .filter(u->u.getId() == ev.getId());
            if(e != null){
                Evenement_Participant par = (Evenement_Participant) user.getUser_participation()
                        .stream().filter(r -> r.getEvenement().getId() == ev.getId());
                user.getUser_participation().remove(par);
                DaoFactory.getUsersDao().update(user);
            }
        }
    }
    public List<Evenement> GetAllProposition(Users user) throws DataException {
        List<Evenement> list = DaoFactory.getEvenementDao().GetEvenementEtatAttendAcceptation(user);
        if(list == null || list.isEmpty()){
            throw new DataException("Vous avez pas de propositions");
        }
        return list;
    }
    public List<Evenement> GetAllReponsesProposition(Users user) throws DataException {
        List<Evenement> list  = user.getUser_participation().stream().map(r -> r.getEvenement())
                .filter(r ->r.getEtat() == Evenement.Etat.Invitation)
                .filter(r ->r.getNbrParticipant() == 2)
                .collect(Collectors.toList());
        if(list == null || list.isEmpty()){
            throw new DataException("Vous avez pas de notifications");
        }
        return list;
    }
    public void AcceptOrRefuseProposition(Users user,int Evenement,Boolean b) throws DataException {
        Evenement evenement = DaoFactory.getEvenementDao().getById(Evenement);
        if(evenement == null){
            throw new DataException("Evenement don't exist");
        }
        if(evenement.getUser_create().getId() != user.getId()){
            throw new DataException("You don't have any permission");
        }
        if(evenement.getNbrParticipant() != 2){
            throw new DataException("You don't have any permission");
        }
        if(b == true){
            evenement.setEtat(Model.Entity.Evenement.Etat.Complet);
        }
        else{
            DaoFactory.getEvenementParticipantDao().remove(evenement.getUsers_participate().get(0));
            evenement.getUsers_participate().remove(evenement.getUsers_participate().get(0));
            evenement.setEtat(Model.Entity.Evenement.Etat.Invitation);
            evenement.setLieu(null);
        }
        DaoFactory.getEvenementDao().update(evenement);
    }
}
