package com.majdamireh.resvsystem.config;

import com.majdamireh.resvsystem.hotel.HotelRepository;
import com.majdamireh.resvsystem.user.User;
import com.majdamireh.resvsystem.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HotelRepository hotelRepository;
    public CustomUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if(user==null)
            throw new UsernameNotFoundException("User not found with username: "+ userName);

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),new ArrayList<>());
    }
}
