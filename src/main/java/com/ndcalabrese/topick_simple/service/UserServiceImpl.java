package com.ndcalabrese.topick_simple.service;

import com.ndcalabrese.topick_simple.dto.UserRegistrationDto;
import com.ndcalabrese.topick_simple.model.Role;
import com.ndcalabrese.topick_simple.model.User;
import com.ndcalabrese.topick_simple.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getUsername(),
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()
                ), Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        // START NEW
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        // END NEW
        // START OLD
//                .orElseThrow(
//                        () -> new UsernameNotFoundException(username)
//                );
        // END OLD
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        return new HashSet<GrantedAuthority>();
//    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
