package com.glasstrainer.service;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

import com.glasstrainer.entity.Pulse;
import com.glasstrainer.entity.Sensor;
import com.glasstrainer.repository.PulseRepository;
import com.glasstrainer.service.interfaces.SensorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PulseService")
public class PulseService implements SensorInterface {

    @Autowired
    PulseRepository pulseRepository;

    @Deprecated
    public Pulse getCurrentPulseData() {
        return pulseRepository.findFirstByOrderByCreatedDesc();
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
