package com.glasstrainer.api.controller;

import com.glasstrainer.entity.Acceleration;
import com.glasstrainer.entity.Gps;
import com.glasstrainer.entity.Pulse;
import com.glasstrainer.entity.Sensor;
import com.glasstrainer.repository.AccelerationRepository;
import com.glasstrainer.repository.GpsRepository;
import com.glasstrainer.repository.PulseRepository;
import com.glasstrainer.service.PulseService;
import com.glasstrainer.service.AccelerationService;
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
import java.util.List;

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
    public List<Sensor> getAccelerationAndPulseData() {
        /*
        Will be replaced and be based on TrainingId
         */
        List<Sensor> sensorList = realTimeService.findLatestByNotLookingTraining();
        return sensorList;
    }

    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    public List<Gps> gpsUpload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username)  throws IOException {
        {
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
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/gps", method = RequestMethod.GET)
    public List<Gps> gpsData() {
        return (List<Gps>)gpsRepository.findAll();
    }

}