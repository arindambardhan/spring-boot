package com.example.demo.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DepartmentDTO(int id, String name, List<CourseDTO> courses) {}
