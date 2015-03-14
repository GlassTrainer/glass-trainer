package com.glasstrainer.api.controller;

import com.glasstrainer.entity.Acceleration;
import com.glasstrainer.entity.Athlete;
import com.glasstrainer.entity.Pulse;
import com.glasstrainer.service.AccelerationService;
import com.glasstrainer.service.PulseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

@RestController
@RequestMapping(value = "/api/acceleration")
public class AccelerationController {

    @Autowired
    private AccelerationService accelerationService;

    @Autowired
    private PulseService pulseService;

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> getCurrentAcceleration() {
        List<Acceleration> accelerations = accelerationService.getCurrentAccelerationData();
        List<Pulse> pulses = pulseService.getCurrentPulseData();

        Map<String, String> result = new HashMap<String, String>();

        for (Acceleration acceleration : accelerations) {
            result.put("acceleration", String.valueOf(acceleration.getResultantAcceleration()));

            Athlete athlete = new Athlete();
            athlete.setEmail("can.srht@gmail.com");
            athlete.setFirstname("Serhat");
            athlete.setSurname("CAN");

            acceleration.setAthlete(athlete);

            if(acceleration.getAthlete() != null) {
                if(acceleration.getAthlete().getFirstname() != null) {
                    result.put("firstname", acceleration.getAthlete().getFirstname());
                }

                if(acceleration.getAthlete().getSurname() != null) {
                    result.put("lastname", acceleration.getAthlete().getSurname());
                }
            }
        }

        for (Pulse pulse : pulses) {
            result.put("pulse", pulse.getRate());
        }


        return result;
    }

}