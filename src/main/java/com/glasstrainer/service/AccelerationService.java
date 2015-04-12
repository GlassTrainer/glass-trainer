package com.glasstrainer.service;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

import com.glasstrainer.entity.Acceleration;
import com.glasstrainer.repository.AccelerationRepository;
import com.glasstrainer.service.interfaces.SensorInterface;
import com.glasstrainer.entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "AccelerationService")
public class AccelerationService implements SensorInterface {

    @Autowired
    AccelerationRepository accelerationRepository;

    @Deprecated
    public Acceleration getCurrentAccelerationData() {
        return accelerationRepository.findFirstByOrderByCreatedDesc();
    }

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
