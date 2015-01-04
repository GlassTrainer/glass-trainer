package com.glasstrainer.persistence;

import com.glasstrainer.entity.Acceleration;
import com.glasstrainer.entity.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Serhat CAN on 07.12.2014.
 */

@Repository
@Transactional
public class AhtleteDAO {

    private final static String ALL_ATHLETES = "SELECT a FROM Athlete a";

    @PersistenceContext
    EntityManager em;

    public Athlete getById(){

        return em.find(Athlete.class, 5l);
    }

    public Athlete create(Athlete athlete) {
        try{
            em.persist(athlete);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return athlete;
    }

    public List<Athlete> getAll() {
       return (List<Athlete>) em.createQuery(ALL_ATHLETES).getResultList();
    }
}
