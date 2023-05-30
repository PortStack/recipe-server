package com.teamz.recipe.service;

import com.teamz.recipe.Dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService{
    UserDto registerUser(UserDto userDto);

}
