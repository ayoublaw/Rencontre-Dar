package Model.Dao;

import Model.Entity.CentreInt;
import Model.Entity.Evenement;
import Model.Entity.Evenement_Participant;
import Model.Entity.SignalCompte;

public class DaoFactory {

    private static UsersDao usersDao;
    private static AddressDao AddressDao;
    private static CentreIntDao CenterIntDao;
    private static EvenementDao EvenementDao;
    private static EvenementParticipantDao EvenementParticipantDao;
    private static SignalCompteDao SignalCompteDao;

    public static UsersDao getUsersDao(){
        if(usersDao == null)
            usersDao = new UsersDao();
        return usersDao;
    }
    public static CentreIntDao getCenterIntDao(){
        if(CenterIntDao == null)
            CenterIntDao = new CentreIntDao();
        return CenterIntDao;
    }
    public static AddressDao getAddressDao(){
        if(AddressDao == null)
            AddressDao = new AddressDao();
        return AddressDao;
    }
    public static EvenementDao getEvenementDao(){
        if(EvenementDao == null){
            EvenementDao = new EvenementDao();
        }
        return EvenementDao;
    }
    public static EvenementParticipantDao getEvenemenytParticipantDao(){
        if(EvenementParticipantDao == null){
            EvenementParticipantDao = new EvenementParticipantDao();
        }
        return EvenementParticipantDao;
    }
    public static SignalCompteDao getSignalCompte(){
        if(SignalCompteDao == null){
            SignalCompteDao = new SignalCompteDao();
        }
        return SignalCompteDao;
    }
}
