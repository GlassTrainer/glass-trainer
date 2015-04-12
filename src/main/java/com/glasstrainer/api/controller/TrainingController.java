package com.glasstrainer.api.controller;

import com.glasstrainer.entity.Training;
import com.glasstrainer.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Serhat CAN on 13.12.2014.
 */

@RestController
@RequestMapping(value = "api/training")
public class TrainingController {

    @Autowired
    TrainingRepository trainingRepository;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Training> trainings() {
        return (List<Training>)trainingRepository.findAll();
    }

    

}
