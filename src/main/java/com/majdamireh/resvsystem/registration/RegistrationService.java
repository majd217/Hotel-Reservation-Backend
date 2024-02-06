package com.majdamireh.resvsystem.registration;

import com.majdamireh.resvsystem.hotel.HotelRepository;
import com.majdamireh.resvsystem.user.User;
import com.majdamireh.resvsystem.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public String saveCredentials(String email, String userName, String password)
    {

        if(userRepository.findByEmailOrUserName(email, userName) !=null)
        {
            System.out.println(hotelRepository.findHotelByHotelID(2));
            return HttpStatus.BAD_REQUEST.toString() + " User is already registered";

        }

        try
        {
            userRepository.save(new User(userName, passwordEncoder.encode(password), email));

            return HttpStatus.OK.toString();
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }
}

//return error codes
//implement try catch for save exception X
