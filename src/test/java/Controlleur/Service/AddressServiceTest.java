package Controlleur.Service;

import Controlleur.Exception.DataException;
import Model.Dao.DaoFactory;
import Model.Entity.Address;
import Model.Entity.Users;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddressServiceTest {
    AddressService adr = new AddressService();
    Users user = DaoFactory.getUsersDao().GetUserByEmail("Ayoublaw123@gmail.com");


    @Test
    public void addAddress() {
        adr.AddAddress(new String[]{"Home","Travail"},new String[]{"5","142"},new String[]{"paul dautier","montmatre"},new String[]{"velizy","paris"},new String[]{"78410","75002"},user);
    }
    @Test
    public void getUserAddress() throws DataException {
        List<Address> list = adr.getUserAddress(user);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.get(0).getNumero(),5);
    }

    @Test
    public void updateAddress() throws DataException {
        adr.UpdateAddress(adr.getUserAddress(user).get(0),"work",null,null,null,null,user);
        List<Address> list = adr.getUserAddress(user);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.get(1).getNom(),"work");
    }

    @Test
    public void deleteAddress() throws DataException {
        adr.DeleteAddress("work",user);
        List<Address> list = adr.getUserAddress(user);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.get(0).getNom(),"work");
    }


}