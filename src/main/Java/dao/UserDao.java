package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public boolean isUsernameExist(String username);

    public User addUser(User user);

    public boolean updatePassword(User user);

    public User login(User user);

    public User findById(int id);
}
