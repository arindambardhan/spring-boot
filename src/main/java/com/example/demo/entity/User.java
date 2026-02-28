package com.example.demo.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private int age;
    private String name;

    @NonNull
    private Address address;

    @NonNull
    private Department department;
}
