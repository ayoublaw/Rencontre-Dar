package Controlleur.Service;

import Controlleur.Exception.DataException;
import Model.Dao.DaoFactory;
import Model.Entity.SignalCompte;
import Model.Entity.Users;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SignalCompteServiceTest {
    SignalCompteService sign = new SignalCompteService();
    Users user1 = DaoFactory.getUsersDao().GetUserByEmail("ayoublaw123@gmail.com");
    Users user2 = DaoFactory.getUsersDao().GetUserByEmail("ayoub.mouhri@usmba.ac.ma");
    @Test
    public void addSignalCompte() {
        SignalCompte s =sign.AddSignalCompte(user2.getNom(),user2.getPrenom(),user1,"blablablablabla");
        Assert.assertNotNull(s);
        Assert.assertEquals(s.getName(),"blablablablabla");
    }

    @Test
    public void listSignalCompte() throws DataException {
        List<SignalCompte> s = sign.ListSignalCompte();
        Assert.assertEquals(s.get(0).getName(),"blablablablabla");
    }

    @Test
    public void confirmSignalCompte() {
        sign.ConfirmSignalCompte(user2.getNom(),user2.getPrenom());
        Users user3 = DaoFactory.getUsersDao().GetUserByEmail("ayoub.mouhri@usmba.ac.ma");
        Assert.assertEquals(user3.getEtat(), Users.Etat.Suspendu);
    }
}