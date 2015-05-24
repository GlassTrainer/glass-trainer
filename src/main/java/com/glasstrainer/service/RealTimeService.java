package com.glasstrainer.service;

import com.glasstrainer.entity.Acceleration;
import com.glasstrainer.entity.Pulse;
import com.glasstrainer.entity.Sensor;
import com.glasstrainer.repository.AccelerationRepository;
import com.glasstrainer.repository.PulseRepository;
import com.glasstrainer.utils.PulseDataCorrection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhat CAN on 12.04.2015.
 */
@Service(value = "RealTimeService")
public class RealTimeService {

    @Autowired
    PulseRepository pulseRepository;

    @Autowired
    AccelerationRepository accelerationRepository;

    @Autowired
    PulseDataCorrection pulseDataCorrection;

    /*
    TODO: will be deleted later. It should be based on TrainingId
     */
    public List<Sensor> findLatestByNotLookingTraining() {

        Pulse pulse =  pulseRepository.findFirstByOrderByCreatedDesc();
        pulse = pulseDataCorrection.fix(pulse);

        Acceleration acceleration = accelerationRepository.findFirstByOrderByCreatedDesc();

        List<Sensor> sensorList = new ArrayList<Sensor>();
        sensorList.add(acceleration);
        sensorList.add(pulse);

        return sensorList;
    }

}
