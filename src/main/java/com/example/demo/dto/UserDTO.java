package com.example.demo.dto;

import lombok.Builder;

@Builder
public record UserDTO(int id, String name, int age, AddressDTO address, DepartmentDTO department) {}
