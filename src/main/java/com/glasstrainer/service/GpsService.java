package com.glasstrainer.service;

import com.glasstrainer.entity.Sensor;
import com.glasstrainer.service.interfaces.SensorInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Serhat CAN on 21.03.2015.
 */

@Service
public class GpsService implements SensorInterface {

    @Override
    public Sensor findLatestForTraining(Long trainingId) {
        return null;
    }

    @Override
    public List<Sensor> findAllForTraining(Long trainingId) {
        return null;
    }

    @Override
    public List<Sensor> saveBatchData(Long trainingId, List<Sensor> data) {
        return null;
    }

    @Override
    public Sensor saveData(Long trainingId, Sensor data) {
        return null;
    }
}
