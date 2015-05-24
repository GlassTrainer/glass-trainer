package com.glasstrainer.api.controller;

import com.glasstrainer.entity.*;
import com.glasstrainer.repository.GpsRepository;
import com.glasstrainer.service.RealTimeService;
import com.glasstrainer.service.UserService;
import com.glasstrainer.utils.GpsDataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

@RestController
@RequestMapping(value = "/api/realtime")
public class RealTimeController {

    @Autowired
    RealTimeService realTimeService;

    @Autowired
    UserService userService;

    @Autowired
    GpsRepository gpsRepository;

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
    public Map getAccelerationAndPulseData() {
        /*
        TODO: Will be replaced and be based on TrainingId
         */
        List<Sensor> sensorList = realTimeService.findLatestByNotLookingTraining();
        Pulse pulse = null;
        Acceleration acceleration = null;

        if(sensorList.size() > 0) {
            acceleration = (Acceleration) sensorList.get(0);
        }
        if(sensorList.size() > 1) {
            pulse = (Pulse) sensorList.get(1);
        }

        User user = userService.getUserById(1l);


        Map<String, String> map = new HashMap<String, String>();
        map.put("time", acceleration.getTime().toString());
        map.put("training", acceleration.getTraining().getName());
        map.put("acceleration", String.valueOf(acceleration.getResultantAcceleration()));
        map.put("pulse", pulse.getRate());
        map.put("firstname", user.getFirstname());
        map.put("lastname", user.getSurname());
        return map;
    }

    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    public List<Gps> gpsUpload(@RequestParam("file") MultipartFile file) throws IOException {

        List<Gps> gpsList = new ArrayList<Gps>();
        if (!file.isEmpty()) {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            gpsList = GpsDataParser.parse(userService.getCurrentUser(), bufferedReader);
            gpsRepository.save(gpsList);
            System.out.println("GPS Array Data Length: " + gpsList.size());
        }

        return gpsList;

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/gps", method = RequestMethod.GET)
    public List<Gps> gpsData() {
        List<Gps> gpsList = (List<Gps>) gpsRepository.findAll();
        System.out.println("Gps list size: " + gpsList.size());
        return gpsList;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/deletegps", method = RequestMethod.GET)
    public boolean deleteGpsData() {
        gpsRepository.deleteAll();

        return true;
    }
}