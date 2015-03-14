package com.glasstrainer.persistence;

import com.glasstrainer.entity.Pulse;
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
public class PulseDAO {

    private final static String RECENT_PULSE = "SELECT s FROM Pulse s order by s.created DESC";

    @PersistenceContext
    private EntityManager em;


    public List<Pulse> getRecentPulseData() {
        Query query = em.createQuery(RECENT_PULSE);
        query.setMaxResults(1);
        return query.getResultList();
    }


}
