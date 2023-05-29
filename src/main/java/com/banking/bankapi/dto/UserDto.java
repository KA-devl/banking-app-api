package com.banking.bankapi.dto;

import com.banking.bankapi.models.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotNull
    @NotEmpty
    @NotBlank (message = "FIRSTNAME_IS_BLANK")
    private String firstname;

    @NotNull
    @NotEmpty
    @NotBlank (message = "LASTNAME_IS_BLANK")
    private String lastname;

    @NotNull
    @NotEmpty
    @NotBlank (message = "EMAIL_IS_BLANK")
    @Email (message = "EMAIL_IS_NOT_VALID")
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank (message = "PASSWORD_IS_BLANK")
    @Size(min = 8, max = 20)
    private String password;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }


}
