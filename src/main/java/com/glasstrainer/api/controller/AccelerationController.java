package com.glasstrainer.api.controller;

import com.glasstrainer.entity.Acceleration;
import com.glasstrainer.entity.User;
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
import java.util.logging.Logger;

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

            User user = new User();
            user.setEmail("can.srht@gmail.com");
            user.setFirstname("Serhat");
            user.setSurname("CAN");

            acceleration.setUser(user);

            if(acceleration.getUser() != null) {
                if(acceleration.getUser().getFirstname() != null) {
                    result.put("firstname", acceleration.getUser().getFirstname());
                }

                if(acceleration.getUser().getSurname() != null) {
                    result.put("lastname", acceleration.getUser().getSurname());
                }
            }
        }

        for (Pulse pulse : pulses) {
            result.put("pulse", pulse.getRate());
        }


        return result;
    }

}