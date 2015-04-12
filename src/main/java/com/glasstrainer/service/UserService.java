package com.glasstrainer.service;

import com.glasstrainer.entity.User;
import com.glasstrainer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Serhat CAN on 21.03.2015.
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return null;
        }

        return (UserDetails) authentication.getPrincipal();
    }

    public User getCurrentUser() {
        UserDetails userDetails = getCurrentUserDetails();
        User user = userRepository.findByUsername(userDetails.getUsername());
        return user;
    }


    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return (List<User>)userRepository.findAll();
    }

}
