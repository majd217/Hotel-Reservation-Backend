package com.majdamireh.resvsystem.user;

import com.majdamireh.resvsystem.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUserName(String userName);
    User findByEmail(String email);
    User findByEmailOrUserName(String email, String userName);

}