package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Address;
import com.example.demo.entity.Course;
import com.example.demo.entity.Department;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private static final List<User> USERS;

    static {
        List<Department> departments = List.of(
            new Department(1, "Engineering", List.of(
                new Course(1, "Data Structures"),
                new Course(2, "Algorithms"),
                new Course(3, "Operating Systems")
            )),
            new Department(2, "Science", List.of(
                new Course(4, "Quantum Physics"),
                new Course(5, "Organic Chemistry"),
                new Course(6, "Thermodynamics")
            )),
            new Department(3, "Arts", List.of(
                new Course(7, "World Literature"),
                new Course(8, "Modern History"),
                new Course(9, "Philosophy")
            )),
            new Department(4, "Business", List.of(
                new Course(10, "Microeconomics"),
                new Course(11, "Business Management"),
                new Course(12, "Financial Accounting")
            )),
            new Department(5, "Medicine", List.of(
                new Course(13, "Human Anatomy"),
                new Course(14, "Pharmacology"),
                new Course(15, "Pathology")
            ))
        );

        String[] firstNames = {
            "Alice", "Bob", "Charlie", "Diana", "Ethan",
            "Fiona", "George", "Hannah", "Ivan", "Julia"
        };
        String[] lastNames = {
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Wilson", "Taylor"
        };
        String[] cities = {
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix",
            "Philadelphia", "San Antonio", "San Diego", "Dallas", "Austin"
        };
        String[] streets = {
            "Main St", "Oak Ave", "Maple Dr", "Cedar Ln", "Pine Rd",
            "Elm St", "Birch Blvd", "Walnut Way", "Ash Ct", "Willow Pl"
        };
        String[] aptLines = {
            "Apt 1A", "Suite 200", "Unit 5B", "Floor 3", "Apt 7C",
            "Suite 101", "Unit 12D", "Floor 6", "Apt 9E", "Suite 305"
        };

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            users.add(new User(
                i + 1,
                firstNames[i % 10] + " " + lastNames[(i / 10) % 10],
                20 + (i % 41),
                new Address(
                    cities[i % 10],
                    (100 + i) + " " + streets[i % 10],
                    aptLines[i % 10]
                ),
                departments.get(i % 5)
            ));
        }

        USERS = Collections.unmodifiableList(users);
    }

    public List<UserDTO> getAllUsers() {
        return USERS.stream().map(this::toDTO)
                .toList();
    }

    private UserDTO toDTO(User user) {
        AddressDTO address = new AddressDTO(
            user.address().city(),
            user.address().line1(),
            user.address().line2()
        );
        List<CourseDTO> courses = user.department().courses().stream()
            .map(c -> new CourseDTO(c.id(), c.name()))
            .toList();
        DepartmentDTO department = new DepartmentDTO(
            user.department().id(),
            user.department().name(),
            courses
        );
        return new UserDTO(user.id(), user.name(), user.age(), address, department);
    }
}
