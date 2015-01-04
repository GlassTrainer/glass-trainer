package com.glasstrainer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Serhat CAN on 11.12.2014.
 */

@Entity
public class Arduino {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String macAddress;
}