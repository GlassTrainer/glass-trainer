package com.glasstrainer.api.controller;

import com.glasstrainer.entity.Gps;
import com.glasstrainer.repository.UserRepository;
import com.glasstrainer.service.GpsService;
import com.glasstrainer.entity.Role;
import com.glasstrainer.entity.User;
import com.glasstrainer.service.UserService;
import com.glasstrainer.utils.GpsDataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    GpsService gpsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public Object getAll() {
        for(User u: userRepository.findAll()) {
            System.out.println(u.getEmail());
        }
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
    public UserDetails getCurrentUserInformation() {
        return userService.getCurrentUserDetails();
    }

    @RequestMapping(value = "/authenticated", method = RequestMethod.GET, produces = "application/json")
    public UserDetails authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return null;
        }

        return (UserDetails) authentication.getPrincipal();
    }

    @RequestMapping(value = "/dummy", method = RequestMethod.GET, produces = "application/json")
    public void createDummyUsers() {
        User user = new User("ferhat", "12345", "fyaldiran@gmail.com", "Ferhat", "Yaldıran", Role.ROLE_TRAINER);
        userRepository.save(user);
    }

}