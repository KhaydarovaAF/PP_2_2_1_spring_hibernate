package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    void printUsers(List<User> usersList);
    void printUserByCar(String model, int series);
}
