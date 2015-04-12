package com.glasstrainer.repository;

import com.glasstrainer.entity.Acceleration;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Serhat CAN on 12.04.2015.
 */
public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    Acceleration findFirstByOrderByCreatedDesc();
}
