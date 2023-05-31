package com.banking.bankapi.services;

import com.banking.bankapi.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);


}
