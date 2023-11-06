package com.majdamireh.resvsystem;

import com.majdamireh.resvsystem.userModel.User;
import com.majdamireh.resvsystem.userModel.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
      @Autowired
      UserRepository userRepository;

    public Boolean checkCredentials(String userName, String password)
    {
        User user = userRepository.findByUserName(userName);
        System.out.println(user);
        if(user == null)
        {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
