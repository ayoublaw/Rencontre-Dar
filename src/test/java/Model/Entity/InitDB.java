package Model.Entity;

import Controlleur.Exception.DataException;
import Controlleur.Service.AuthentificationService;
import Model.Dao.DaoFactory;
import org.junit.Assert;
import org.junit.Test;

public class InitDB{
@Test
public void InitUserDB() throws DataException {
    AuthentificationService auth = new AuthentificationService();
    auth.register("Test2@gmail.com","MOuh","Ayou",25,"M","336", new String[]{"Sport", "Musique"});
    Assert.assertNotNull(DaoFactory.getUsersDao().GetUserByEmail("test2@gmail.com"));
    Assert.assertNotNull(DaoFactory.getUsersDao().GetUserByEmail("test2@gmail.com").getCentreInteret());
    Assert.assertEquals(DaoFactory.getCenterIntDao().selectAll().get(0).getName(),"Sport");
}
}
