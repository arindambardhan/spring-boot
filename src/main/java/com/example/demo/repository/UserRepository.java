package com.example.demo.repository;

import com.example.demo.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(int id);
}
