package com.glasstrainer.repository;

import com.glasstrainer.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Serhat CAN on 12.04.2015.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);

}
