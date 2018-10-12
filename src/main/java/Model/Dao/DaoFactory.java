package Model.Dao;

public class DaoFactory {

    private static UsersDao usersDao;
    private static AddressDao AddressDao;

    public static UsersDao getUsersDao(){
        if(usersDao == null)
            usersDao = new UsersDao();
        return usersDao;
    }
    public static AddressDao getAddressDao(){
        if(AddressDao == null)
            AddressDao = new AddressDao();
        return AddressDao;
    }

}
