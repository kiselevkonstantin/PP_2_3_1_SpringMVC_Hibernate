package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(int id);

    void updateUser(int id, User user);

    User getUserByID(int id);
}
