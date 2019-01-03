package dao;

import model.User;

public interface UserDao {

    public boolean isUsernameExist(String username);

    public User addUser(User user);

    public boolean updatePassword(User user);

    public User login(User user);

    public User findById(int id);
}
