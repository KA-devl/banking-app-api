package com.banking.bankapi.controllers;

import com.banking.bankapi.dto.UserDto;
import com.banking.bankapi.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserServiceImpl userServiceImpl;
//
//    @PostMapping("/")
//    public ResponseEntity<Integer> save(
//        @RequestBody UserDto userDto
//    ) {
//        return ResponseEntity.ok(userServiceImpl.update(userDto));
//    }
//
//
//}
