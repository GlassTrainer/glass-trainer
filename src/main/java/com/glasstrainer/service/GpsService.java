package com.glasstrainer.service;

import com.glasstrainer.entity.Gps;
import com.glasstrainer.entity.Sensor;
import com.glasstrainer.persistence.GpsDAO;
import com.glasstrainer.service.interfaces.SensorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Serhat CAN on 21.03.2015.
 */

@Service
public class GpsService implements SensorInterface {

    @Autowired
    GpsDAO gpsDAO;

    @Deprecated
    public List<Gps> getAllGps() {
        return gpsDAO.getAllGps();
    }

    @Deprecated
    public List<Gps> createList(List<Gps> gpsList) {
        return gpsDAO.createList(gpsList);
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
