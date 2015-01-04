package com.glasstrainer.persistence;

import com.glasstrainer.entity.Acceleration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

@Repository
@Transactional
public class AccelerationDAO {

    private final static String RECENT_ACCELERATION = "SELECT s FROM Acceleration s order by s.created DESC";

    @PersistenceContext
    private EntityManager em;


    public List<Acceleration> getAccelerationData(){
        Query query = em.createQuery(RECENT_ACCELERATION);
        query.setMaxResults(10);
        List<Acceleration> accelerations = query.getResultList();
        return query.getResultList();
    }



}
