package com.glasstrainer.service;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

import com.glasstrainer.entity.Acceleration;
import com.glasstrainer.persistence.AccelerationDAO;
import com.glasstrainer.service.interfaces.SensorInterface;
import com.glasstrainer.entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "AccelerationService")
public class AccelerationService implements SensorInterface {

    @Autowired
    AccelerationDAO accelerationDAO;

    @Deprecated
    public List<Acceleration> getCurrentAccelerationData() {
        return accelerationDAO.getAccelerationData();
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
