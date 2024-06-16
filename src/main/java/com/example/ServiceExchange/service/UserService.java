package com.example.ServiceExchange.service;

import com.example.ServiceExchange.model.User;
import com.example.ServiceExchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email, String password, Date registerDate) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRegisterDate(registerDate);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(Long userId, String name, String email, String password, Date registerDate) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setName(name);
            existingUser.setEmail(email);
            existingUser.setPassword(password);
            existingUser.setRegisterDate(registerDate);
            return userRepository.save(existingUser);
        }
        return null;
    }

    public boolean deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            userRepository.delete(existingUser);
            return true;
        }
        return false;
    }
}
