package com.majdamireh.resvsystem.registration;

import com.majdamireh.resvsystem.exceptions.CustomRunTimeException;
import com.majdamireh.resvsystem.exceptions.StatusCodes;
import com.majdamireh.resvsystem.hotel.HotelRepository;
import com.majdamireh.resvsystem.user.User;
import com.majdamireh.resvsystem.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
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
	
	
	public void saveCredentials(String email, String userName, String password) {
		
		if (email == "" || email == null) {
			throw new CustomRunTimeException(StatusCodes.Email.getMessage(), StatusCodes.Email.getStatusCode());
		}
		//should I create another exception?
		if (userName == "" || userName == null) {
			throw new CustomRunTimeException(StatusCodes.UserName.getMessage(), StatusCodes.UserName.getStatusCode());
		}
		if (password == "" || password == null) {
			throw new CustomRunTimeException(StatusCodes.Password.getMessage(), StatusCodes.Password.getStatusCode());
		}
		
		if (userRepository.findByEmailOrUserName(email, userName) != null) {
			throw new CustomRunTimeException(StatusCodes.UserExists.getMessage(), StatusCodes.UserExists.getStatusCode());
		}
		
		try {
			userRepository.save(new User(userName, passwordEncoder.encode(password), email));
			return;
		} catch (IllegalArgumentException | OptimisticLockingFailureException exception) {
			throw new CustomRunTimeException("", StatusCodes.DBError.getStatusCode());
		}
	}
}

//return error codes
//implement try catch for save exception X
