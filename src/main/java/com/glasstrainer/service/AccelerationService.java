package com.glasstrainer.service;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

import com.glasstrainer.entity.Acceleration;
import com.glasstrainer.persistence.AccelerationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "AccelerationService")
public class AccelerationService {

    @Autowired
    AccelerationDAO accelerationDAO;

    public List<Acceleration> getCurrentAccelerationData() {
        return accelerationDAO.getAccelerationData();
    }

}
