package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.Course;
import com.example.demo.entity.Department;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private static final Department ENGINEERING = Department.builder()
            .id(1).name("Engineering")
            .courses(List.of(
                    Course.builder().id(1).name("Data Structures").build(),
                    Course.builder().id(2).name("Algorithms").build(),
                    Course.builder().id(3).name("Operating Systems").build()
            )).build();

    private static final Department SCIENCE = Department.builder()
            .id(2).name("Science")
            .courses(List.of(
                    Course.builder().id(4).name("Quantum Physics").build(),
                    Course.builder().id(5).name("Organic Chemistry").build(),
                    Course.builder().id(6).name("Thermodynamics").build()
            )).build();

    private static final Department ARTS = Department.builder()
            .id(3).name("Arts")
            .courses(List.of(
                    Course.builder().id(7).name("World Literature").build(),
                    Course.builder().id(8).name("Modern History").build(),
                    Course.builder().id(9).name("Philosophy").build()
            )).build();

    private static final Department BUSINESS = Department.builder()
            .id(4).name("Business")
            .courses(List.of(
                    Course.builder().id(10).name("Microeconomics").build(),
                    Course.builder().id(11).name("Business Management").build(),
                    Course.builder().id(12).name("Financial Accounting").build()
            )).build();

    private static final Department MEDICINE = Department.builder()
            .id(5).name("Medicine")
            .courses(List.of(
                    Course.builder().id(13).name("Human Anatomy").build(),
                    Course.builder().id(14).name("Pharmacology").build(),
                    Course.builder().id(15).name("Pathology").build()
            )).build();

    private static final List<User> USERS = List.of(
            User.builder().id(1).name("Alice Smith").age(28)
                    .address(Address.builder().city("New York").line1("101 Main St").line2("Apt 1A").build())
                    .department(ENGINEERING).build(),
            User.builder().id(2).name("Bob Johnson").age(34)
                    .address(Address.builder().city("Los Angeles").line1("202 Oak Ave").line2("Suite 200").build())
                    .department(SCIENCE).build(),
            User.builder().id(3).name("Charlie Williams").age(22)
                    .address(Address.builder().city("Chicago").line1("303 Maple Dr").line2("Unit 5B").build())
                    .department(ARTS).build(),
            User.builder().id(4).name("Diana Brown").age(45)
                    .address(Address.builder().city("Houston").line1("404 Cedar Ln").line2("Floor 3").build())
                    .department(BUSINESS).build(),
            User.builder().id(5).name("Ethan Jones").age(31)
                    .address(Address.builder().city("Phoenix").line1("505 Pine Rd").line2("Apt 7C").build())
                    .department(MEDICINE).build(),
            User.builder().id(6).name("Fiona Garcia").age(27)
                    .address(Address.builder().city("Philadelphia").line1("606 Elm St").line2("Suite 101").build())
                    .department(ENGINEERING).build(),
            User.builder().id(7).name("George Miller").age(39)
                    .address(Address.builder().city("San Antonio").line1("707 Birch Blvd").line2("Unit 12D").build())
                    .department(SCIENCE).build(),
            User.builder().id(8).name("Hannah Davis").age(52)
                    .address(Address.builder().city("San Diego").line1("808 Walnut Way").line2("Floor 6").build())
                    .department(ARTS).build(),
            User.builder().id(9).name("Ivan Wilson").age(24)
                    .address(Address.builder().city("Dallas").line1("909 Ash Ct").line2("Apt 9E").build())
                    .department(BUSINESS).build(),
            User.builder().id(10).name("Julia Taylor").age(36)
                    .address(Address.builder().city("Austin").line1("1010 Willow Pl").line2("Suite 305").build())
                    .department(MEDICINE).build()
    );

    public List<User> findAll() {
        return USERS;
    }

    public User findById(int id) {
        Map<Integer, User> usersById = getUsersById();
        return usersById.get(id);
    }

    private Map<Integer, User> getUsersById() {
        return USERS.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
    }
}
