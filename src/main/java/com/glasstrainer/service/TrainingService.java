package com.glasstrainer.service;

import com.glasstrainer.entity.Training;
import com.glasstrainer.service.interfaces.TrainingInterface;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Serhat CAN on 11.04.2015.
 */

@Service
public class TrainingService implements TrainingInterface {


    @Override
    public Training createTraining(String name) {
        return null;
    }

    @Override
    public boolean endTraining(Long trainingId) {
        return false;
    }

    @Override
    public List<Training> getTrainingBetweenDates(Date startTime, Date endTime) {
        return null;
    }

    @Override
    public List<Training> getTrainingByName(String name) {
        return null;
    }

    @Override
    public Training findTraining(Long id) {
        return null;
    }

    @Override
    public List<Training> findLatestTrainings() {
        return null;
    }
}
