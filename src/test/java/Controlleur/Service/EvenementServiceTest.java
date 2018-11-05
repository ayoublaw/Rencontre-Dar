package Controlleur.Service;

import Controlleur.Exception.DataException;
import Model.Dao.DaoFactory;
import Model.Entity.Evenement;
import Model.Entity.Users;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

public class EvenementServiceTest {
    EvenementService evenementService = new EvenementService();
    Users user1 = DaoFactory.getUsersDao().GetUserByEmail("test@gmail.com");
    Users user2 = DaoFactory.getUsersDao().GetUserByEmail("test2@gmail.com");
    Evenement e ;
    @Test
    public void addEvenement() throws ParseException, DataException {
        e = evenementService.AddEvenement(user1,"test Eve",null,"1","05/11/2018 22:30:00","Sport");
    }

    @Test
    public void listEvenementCanParticipate() throws DataException {
        List<Evenement> list= evenementService.ListEvenementCanParticipate(user2);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.get(0).getDescription(),"test Eve");
    }

    @Test
    public void participateInEvenement() throws DataException {
        evenementService.ParticipateInEvenement(user2,14,new String[]{"test1","test2"});
    }

    @Test
    public void listEvenementCreateActif() throws DataException {
        List<Evenement> list= evenementService.ListEvenementCreateActif(user1);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.get(0).getDescription(),"test Eve");

    }

    @Test
    public void listEvenementCreate() throws DataException {

        List<Evenement> list= evenementService.ListEvenementCreate(user1);
        Assert.assertNotNull(list);

    }

    @Test
    public void listEvenementParticipateActif() throws DataException {
        List<Evenement> list2= evenementService.ListEvenementParticipateActif(user2);
        Assert.assertNotNull(list2);
        Assert.assertEquals(list2.get(0).getDescription(),"test Eve");
    }

    @Test
    public void listEvenementParticipate() throws DataException {

        List<Evenement> list= evenementService.ListEvenementParticipate(user1);
        List<Evenement> list2= evenementService.ListEvenementParticipate(user2);
        Assert.assertNull(list2);
        Assert.assertNull(list);
    }
    @Test
    public void AcceptOrRefuseProposition() throws DataException {
        evenementService.AcceptOrRefuseProposition(user1,14,true,"test1");
        List<Evenement> list = evenementService.ListEvenementCreateActif(user1);
        Assert.assertEquals(list.get(0).getEtat(), Evenement.Etat.Complet);
    }


    @Test
    public void deleteEvenement() throws DataException {
        evenementService.DeleteEvenement(user1,16);
        List<Evenement> list= evenementService.ListEvenementCreateActif(user1);
        Assert.assertNull(list);
    }


    @Test
    public void deleteEvenementParticipate() throws DataException {
        evenementService.DeleteEvenementParticipate(user2,14);
        List<Evenement> list= evenementService.ListEvenementParticipateActif(user2);
        Assert.assertNull(list);
    }
}