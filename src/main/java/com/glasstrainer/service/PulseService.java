package com.glasstrainer.service;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

import com.glasstrainer.entity.Pulse;
import com.glasstrainer.persistence.PulseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PulseService")
public class PulseService {

    @Autowired
    PulseDAO pulseDAO;

    public List<Pulse> getCurrentPulseData() {
        return pulseDAO.getRecentPulseData();
    }

}
