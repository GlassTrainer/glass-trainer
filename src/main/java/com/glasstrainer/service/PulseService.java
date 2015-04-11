package com.glasstrainer.service;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

import com.glasstrainer.persistence.PulseDAO;
import com.glasstrainer.entity.Pulse;
import com.glasstrainer.entity.Sensor;
import com.glasstrainer.service.interfaces.SensorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PulseService")
public class PulseService implements SensorInterface {

    @Autowired
    PulseDAO pulseDAO;

    @Deprecated
    public List<Pulse> getCurrentPulseData() {
        return pulseDAO.getRecentPulseData();
    }


    @Override
    public List<Sensor> listLatestData(Long trainingId) {
        return null;
    }

    @Override
    public Sensor getLastData(Long trainingId) {
        return null;
    }

    @Override
    public List<Sensor> getAllData(Long trainingId) {
        return null;
    }

    @Override
    public List<Sensor> createBatchData(Long trainingId, List<Sensor> data) {
        return null;
    }

    @Override
    public Sensor createData(Long trainingId, Sensor data) {
        return null;
    }
}
