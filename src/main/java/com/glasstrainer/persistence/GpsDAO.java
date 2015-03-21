package com.glasstrainer.persistence;

import com.glasstrainer.entity.Gps;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Serhat CAN on 21.03.2015.
 */

@Repository
@Transactional
public class GpsDAO {

    @PersistenceContext
    EntityManager em;

    public Gps create(Gps gps) {
        try {
            em.persist(gps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gps;
    }

    public List<Gps> create(List<Gps> gpsList) {
        try {
            em.persist(gpsList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gpsList;
    }

}
