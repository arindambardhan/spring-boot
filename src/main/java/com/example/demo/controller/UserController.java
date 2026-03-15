package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        log.info("Producer App: getUsers completed successfully");
        return allUsers;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") int id) {
        UserDTO userById = userService.getUserById(id);
        log.info("Producer App: getUserById completed successfully");
        return userById;
    }
}
