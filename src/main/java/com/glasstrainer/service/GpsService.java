package com.glasstrainer.service;

import com.glasstrainer.entity.Gps;
import com.glasstrainer.persistence.GpsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Serhat CAN on 21.03.2015.
 */

@Service
public class GpsService {

    @Autowired
    GpsDAO gpsDAO;

    public List<Gps> create(List<Gps> gpsList) {
        return gpsDAO.create(gpsList);
    }

}
