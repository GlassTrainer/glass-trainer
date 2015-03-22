package com.glasstrainer.persistence;

import com.glasstrainer.entity.Gps;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Serhat CAN on 21.03.2015.
 */

@Repository
@Transactional
public class GpsDAO {

    private final static String ALL_GPS = "SELECT g FROM Gps g";

    @PersistenceContext
    EntityManager em;

    public List<Gps> getAllGps() {
        Query query = em.createQuery(ALL_GPS);
        return query.getResultList();
    }

    public Gps create(Gps gps) {
        try {
            em.persist(gps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gps;
    }

    public List<Gps> createList(List<Gps> gpsList) {
        try {
            //em.persist(gpsList);
            for(Gps gps: gpsList ) {
                create(gps);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gpsList;
    }

}
