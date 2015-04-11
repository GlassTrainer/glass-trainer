package com.glasstrainer.api.controller;

import com.glasstrainer.service.PulseService;
import com.glasstrainer.service.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

@RestController
@RequestMapping(value = "/api/realtime")
public class RealTimeController {

    @Autowired
    private AccelerationService accelerationService;

    @Autowired
    private PulseService pulseService;

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
    public String getCurrent() {

        return null;
    }

}