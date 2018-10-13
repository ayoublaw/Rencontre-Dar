package Model.Entity;

import Model.Dao.DaoFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class DBTest {
    @Test
    public void UsersAddressTest(){
    Users user = DaoFactory.getUsersDao().getById(1);
    for (Address adr : user.getAddress() ){
        System.out.println(adr.getId()); }
    }
    @Test
    public void AllTablesTest(){
    }



}