package com.glasstrainer.api.controller;

import com.glasstrainer.entity.Athlete;
import com.glasstrainer.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Serhat CAN on 13.12.2014.
 */

@RestController
@RequestMapping(value = "/api/athlete")
public class AthleteController {

    @Autowired
    AthleteService athleteService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public void createAthlete(@RequestBody Athlete athlete) {
        System.out.println("Athlete: " + athlete.toString());
        System.out.println("Username: " + athlete.getUsername());
        System.out.println("Athlete pass: " + athlete.getPassword());
        athleteService.create(athlete);
    }

    @RequestMapping(value = "/5", method = RequestMethod.GET,  produces = "application/json")
    public Athlete getByAthlete() {
        return athleteService.getById();
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<Athlete> getAll() {
        return athleteService.getAll();
    }

}

