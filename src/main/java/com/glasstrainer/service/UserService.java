package com.glasstrainer.service;

import com.glasstrainer.entity.User;
import com.glasstrainer.persistence.UserDAO;
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
    UserDAO userDAO;

    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return null;
        }

        return (UserDetails) authentication.getPrincipal();
    }

    public User getCurrentUser() {
        UserDetails userDetails = getCurrentUserDetails();
        User user = userDAO.getByUsername(userDetails.getUsername());
        return user;
    }


    public User getUserById(Long id) {
        return userDAO.getById(id);
    }

    public User create(User user) {
        return userDAO.create(user);
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

}
