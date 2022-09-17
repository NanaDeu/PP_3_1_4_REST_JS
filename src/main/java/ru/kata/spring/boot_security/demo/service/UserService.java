package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User getUserById(long id);

    User updateUser(long id, User user);

    User update(User user);

    User save(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    User findUserByUsername(String username);

    List<Role> listRoles();

}
