package com.glasstrainer.api.controller;

import com.glasstrainer.entity.Gps;
import com.glasstrainer.entity.Role;
import com.glasstrainer.entity.User;
import com.glasstrainer.service.GpsService;
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
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    GpsService gpsService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public User getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    public List<Gps> gpsUpload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username)
            throws IOException {
        {
            List<Gps> gpsList = new ArrayList<Gps>();

            if (!file.isEmpty()) {
                InputStream inputStream = file.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                gpsList = GpsDataParser.parse(userService.getCurrentUser(), bufferedReader);

                gpsService.create(gpsList);
                System.out.println(gpsList);

            }

            System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), username));

            return gpsList;
        }
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

        User user = new User("Serhat", "12345", "can.srht@gmail.com", "Serhat", "CAN", Role.ROLE_ADMIN);
        userService.create(user);

    }

}