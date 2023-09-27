package plazma.ups.eshop.dao;

import plazma.ups.eshop.entity.User;

public interface UserDao {

    User login(String username, String password);

}
