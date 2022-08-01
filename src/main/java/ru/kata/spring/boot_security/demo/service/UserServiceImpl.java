package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userDao) {
        this.roleRepository = roleRepository;
        this.userRepository = userDao;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User updateUser(long id, User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findUserByUsername(name);
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

}
