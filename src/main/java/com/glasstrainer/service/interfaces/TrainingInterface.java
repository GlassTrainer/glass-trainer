package com.glasstrainer.service.interfaces;

import com.glasstrainer.entity.Training;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Serhat CAN on 11.04.2015.
 */

@Component
public interface TrainingInterface {

    Training createTraining(String name);

    boolean endTraining(Long trainingId);

    List<Training> getTrainingBetweenDates(Date startTime, Date endTime);

    List<Training> getTrainingByName(String name);

    Training findTraining(Long id);

    List<Training> findLatestTrainings();
}
