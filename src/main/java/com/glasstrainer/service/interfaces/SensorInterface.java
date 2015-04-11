package com.glasstrainer.service.interfaces;

import com.glasstrainer.entity.Sensor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Serhat CAN on 11.04.2015.
 */

@Component
public interface SensorInterface {

    /*
       Gets latest 10 sensor data for the given training id
     */
    List<Sensor> listLatestData(Long trainingId);

    Sensor getLastData(Long trainingId);

    List<Sensor> getAllData(Long trainingId);

    List<Sensor> createBatchData(Long trainingId, List<Sensor> data);

    Sensor createData(Long trainingId, Sensor data);
}
