package com.glasstrainer.repository;

import com.glasstrainer.entity.Pulse;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Serhat CAN on 12.04.2015.
 */
public interface PulseRepository extends CrudRepository<Pulse, Long> {

    Pulse findFirstByOrderByCreatedDesc();
}
