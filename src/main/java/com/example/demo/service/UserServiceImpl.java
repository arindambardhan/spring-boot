package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(FactoryHelperUtil::toDTO)
                .toList();
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id);
        if (Objects.nonNull(user)) {
            return FactoryHelperUtil.toDTO(user);
        }
        throw new UserNotFoundException(id, HttpStatus.NOT_FOUND);
    }
}

