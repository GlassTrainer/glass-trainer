package com.glasstrainer.service;

import com.glasstrainer.entity.Athlete;
import com.glasstrainer.persistence.AhtleteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

@Service
public class AthleteService {

    @Autowired
    AhtleteDAO athleteDAO;

    public Athlete getAthleteById(Long id) {
        return athleteDAO.getById(id);
    }

    public Athlete create(Athlete athlete) {
        return athleteDAO.create(athlete);
    }

    public List<Athlete> getAll() {
        return athleteDAO.getAll();
    }
}
