package com.example.demo.entity;

import java.util.List;

public record Department(int id, String name, List<Course> courses) {}
