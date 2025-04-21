package com.example.OnlineFoodOrdering.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineFoodOrdering.config.JwtProvider;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;


    
    @Override
    public User findUserByJwtToken(String jwt) throws Exception{
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        System.out.println(email);
        User user = findUserByEmail(email);
        return user;
    }
    
    @Override
    public User findUserByEmail(String email) throws Exception{
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("user not found");
        }
        return user;
    }

    

}

// @Override
// public User findUserByJwtToken(String jwt) throws Exception {
//     String email = jwtProvider.getEmailFromJwtToken(jwt);
//     List<String> roles = jwtProvider.getRolesFromJwtToken(jwt);

//     System.out.println("Extracted Email: " + email);
//     System.out.println("Extracted Roles: " + roles);

//     User user = findUserByEmail(email);
//     System.out.println("User Found: " + user.getEmail());

//     return user;
// }