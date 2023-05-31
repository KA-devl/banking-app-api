package com.banking.bankapi.services.impl;

import com.banking.bankapi.dto.AccountDto;
import com.banking.bankapi.dto.UserDto;
import com.banking.bankapi.models.User;
import com.banking.bankapi.repositories.AccountRepository;
import com.banking.bankapi.repositories.UserRepository;
import com.banking.bankapi.services.AccountService;
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
    private final AccountService accountService;

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

    @Override
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        //activate user
        user.setActive(true);
        //create a bank account for the user
        AccountDto accountDto = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(accountDto);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        //deactivate user
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
}
