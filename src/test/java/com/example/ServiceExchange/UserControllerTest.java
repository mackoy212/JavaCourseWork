package com.example.ServiceExchange;

import com.example.ServiceExchange.model.User;
import com.example.ServiceExchange.repository.UserRepository;
import com.example.ServiceExchange.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateUser() {
        Date currentDate = new Date();
        User user = userService.createUser("John Doe", "john.doe@example.com", "password123", currentDate);
        assertNotNull(user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(currentDate, user.getRegisterDate());
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
    }

    @Test
    public void testGetUserById() {
        Date currentDate = new Date();
        User user = userService.createUser("Jane Smith", "jane.smith@example.com", "password456", currentDate);
        Long userId = user.getId();

        User retrievedUser = userService.getUserById(userId);
        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getId());
        assertEquals("Jane Smith", retrievedUser.getName());
        assertEquals("jane.smith@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
        Date currentDate = new Date();
        User user = userService.createUser("Alice Johnson", "alice.johnson@example.com", "password789", currentDate);
        Long userId = user.getId();

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setName("Alice Thompson");
        updatedUser.setEmail("alice.thompson@example.com");
        updatedUser.setPassword("newpassword");
        updatedUser.setRegisterDate(currentDate);

        User result = userService.updateUser(userId, updatedUser.getName(), updatedUser.getEmail(), updatedUser.getPassword(), updatedUser.getRegisterDate());
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Alice Thompson", result.getName());
        assertEquals("alice.thompson@example.com", result.getEmail());
    }

    @Test
    public void testDeleteUser() {
        Date currentDate = new Date();
        User user = userService.createUser("Mark Davis", "mark.davis@example.com", "password123", currentDate);
        Long userId = user.getId();

        boolean deleted = userService.deleteUser(userId);
        assertTrue(deleted);

        User deletedUser = userService.getUserById(userId);
        assertNull(deletedUser);
    }
}
