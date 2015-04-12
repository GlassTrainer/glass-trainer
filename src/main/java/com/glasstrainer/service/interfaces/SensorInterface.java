package com.glasstrainer.service.interfaces;

import com.glasstrainer.entity.Sensor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Serhat CAN on 11.04.2015.
 */

@Component
public interface SensorInterface {

    Sensor findLatestForTraining(Long trainingId);

    List<Sensor> findAllForTraining(Long trainingId);

    List<Sensor> saveBatchData(Long trainingId, List<Sensor> data);

    Sensor saveData(Long trainingId, Sensor data);
}
