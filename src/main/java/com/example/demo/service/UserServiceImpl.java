package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    private UserDTO toDTO(User user) {
        AddressDTO address = AddressDTO.builder()
                .city(user.getAddress().getCity())
                .line1(user.getAddress().getLine1())
                .line2(user.getAddress().getLine2())
                .build();

        List<CourseDTO> courses = user.getDepartment().getCourses().stream()
                .map(c -> CourseDTO.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .build())
                .toList();

        DepartmentDTO department = DepartmentDTO.builder()
                .id(user.getDepartment().getId())
                .name(user.getDepartment().getName())
                .courses(courses)
                .build();

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .address(address)
                .department(department)
                .build();
    }
}
