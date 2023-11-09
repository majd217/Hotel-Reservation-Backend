package com.majdamireh.resvsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    public boolean saveCredentials(String email, String userName, String password)
    {
        //check if username or email exist
        if(userRepository.findByEmail(email) == null && userRepository.findByUserName(userName) == null)
        {
            userRepository.save(new User(userName, password, email));
            return true;
        }

        return false;
    }
}
