package Model.Entity;

import Model.Dao.DaoFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class DBTest {
    @Test
    public void UsersAddressTest(){
    Users user = DaoFactory.getUsersDao().GetUserByEmail("Ayoublaw123@gmail.com");
        System.out.println(user.getPassword()); }

    @Test
    public void AllTablesTest(){

    }



}