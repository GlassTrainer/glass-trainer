package com.glasstrainer.api.controller;

import com.glasstrainer.entity.Athlete;
import com.glasstrainer.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Serhat CAN on 13.12.2014.
 */

@RestController
@RequestMapping(value = "/api/athlete")
public class AthleteController {

    @Autowired
    AthleteService athleteService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public void createAthlete(@RequestBody Athlete athlete) {
        athleteService.create(athlete);
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Athlete getAthleteById(@PathVariable Long id) {

        return athleteService.getAthleteById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Athlete> getAll() {
        return athleteService.getAll();
    }

}

