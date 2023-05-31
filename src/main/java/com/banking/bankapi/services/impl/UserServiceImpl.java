package com.banking.bankapi.services.impl;

import com.banking.bankapi.dto.UserDto;
import com.banking.bankapi.repositories.UserRepository;
import com.banking.bankapi.services.UserService;
import com.banking.bankapi.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto); //validate the object before saving it
        return userRepository.save(UserDto.toEntity(dto)).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()//Transform all users to userDto
                .map(user -> UserDto.fromEntity(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(user -> UserDto.fromEntity(user))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        //check before delete
        userRepository.deleteById(id);
    }
}
