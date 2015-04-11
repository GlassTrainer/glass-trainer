/**
 *
 */
package com.glasstrainer.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Serhat CAN
 */

@Entity
public class Pulse implements  Sensor, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Training training;

    @Column(nullable = false)
    private String rate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable = false)
    private Date created;

    public Pulse() {

    }

    public Pulse(String rate) {
        this.rate = rate;
    }

    public Pulse(Training training, String rate) {
        this.training = training;
        this.rate = rate;
    }

    // Getters and Setters

    public Date getTime() {
        return created;
    }

    @PrePersist
    protected void setTime() {
        created = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
