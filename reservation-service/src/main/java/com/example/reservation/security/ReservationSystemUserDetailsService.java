package com.example.reservation.security;

import com.example.reservation.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReservationSystemUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<com.example.reservation.model.User> optional = userRepository.findById(s);
        com.example.reservation.model.User dbUser = optional.orElseThrow(() -> new UsernameNotFoundException(s));
        String[] authorities={};
        if(s.equals("admin@gmail.com")){
            authorities=new String[]{"ADMIN","USER"};
        }else{
            authorities=new String[]{"USER"};
        }
        User user =new User(dbUser.getEmail(),dbUser.getPassword(), AuthorityUtils.createAuthorityList(authorities));
        return user;
    }
}
