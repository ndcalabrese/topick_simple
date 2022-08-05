package com.ndcalabrese.topick_simple.service;

import com.ndcalabrese.topick_simple.dto.UserRegistrationDto;
import com.ndcalabrese.topick_simple.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
