package com.banking.bankapi.services.impl;

import com.banking.bankapi.dto.UserDto;
import com.banking.bankapi.repositories.UserRepository;
import com.banking.bankapi.services.UserService;
import com.banking.bankapi.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        return userRepository.save(UserDto.toEntity(dto)).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
