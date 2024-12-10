package org.example;


import org.example.model.User;
import org.example.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.saveUser("Vbkfyf", "Богданова", (byte) 20);
        userService.getAllUsers();






    }
}
