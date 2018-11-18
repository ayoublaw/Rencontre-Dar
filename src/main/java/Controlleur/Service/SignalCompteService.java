package Controlleur.Service;

import Controlleur.Exception.DataException;
import Model.Dao.DaoFactory;
import Model.Entity.SignalCompte;
import Model.Entity.Users;

import java.util.List;

public class SignalCompteService {
    public SignalCompte AddSignalCompte(String nom, String prenom, Users currentuser,String description){
        SignalCompte signalCompte = new SignalCompte();
        Users user = DaoFactory.getUsersDao().GetUserByName(nom,prenom);
        signalCompte.setName(description);
        signalCompte.setUser_create_signal(currentuser);
        signalCompte.setUser_signal(user);
        DaoFactory.getSignalCompte().Save(signalCompte);
        return signalCompte;
    }
    public List<SignalCompte> ListSignalCompte() throws DataException {
     List<SignalCompte> list =DaoFactory.getSignalCompte().selectAll();
     if(list == null || list.isEmpty()){
         throw new DataException("SignalCompte is empty");
     }
     return list;
    }
    public void ConfirmSignalCompte(String Email){
        Users user = DaoFactory.getUsersDao().GetUserByEmail(Email);
        List<SignalCompte> userSignal = DaoFactory.getSignalCompte().getSignalCompteForoneUser(user);
        user.setEtat(Users.Etat.Suspendu);
        DaoFactory.getUsersDao().update(user);
        for(SignalCompte s : userSignal){
            DaoFactory.getSignalCompte().remove(s);
        }
    }
}
