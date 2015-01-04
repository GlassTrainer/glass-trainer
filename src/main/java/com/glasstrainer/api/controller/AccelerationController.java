package com.glasstrainer.api.controller;

import com.glasstrainer.entity.Acceleration;
import com.glasstrainer.entity.Athlete;
import com.glasstrainer.entity.Role;
import com.glasstrainer.persistence.AhtleteDAO;
import com.glasstrainer.service.AccelerationService;
import org.dom4j.util.AttributeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Query;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

@RestController
@RequestMapping(value = "/api/acceleration")
public class AccelerationController {

    @Autowired
    private AccelerationService accelerationService;

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
    public List<Acceleration> getCurrentAcceleration() {
        return accelerationService.getCurrentAccelerationData();
    }


}
