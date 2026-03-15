package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class FactoryHelperUtil {

    public static UserDTO toDTO(User user) {
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

    @Slf4j
    static class ContextualOp {
        static void simulateLatency() {
            try {
                ThreadLocalRandom rng = ThreadLocalRandom.current();
                long delay;
                int roll = rng.nextInt(100);
                if (roll < 60) {
                    delay = rng.nextLong(10, 50);      // 60%: fast  10–50ms
                } else if (roll < 90) {
                    delay = rng.nextLong(50, 150);     // 30%: normal 50–150ms
                } else {
                    delay = rng.nextLong(150, 500);    // 10%: slow spike 150–500ms
                }
                log.debug("Simulated latency: {} ms", delay);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}