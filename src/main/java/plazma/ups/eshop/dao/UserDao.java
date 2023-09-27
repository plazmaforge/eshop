package plazma.ups.eshop.dao;

public interface UserDao {

    boolean existsUser(String username, String password);

}
