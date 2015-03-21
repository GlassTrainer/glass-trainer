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
public class Pulse implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    User user;

    @Column(nullable = false)
    private String rate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable = false)
    private Date created;

    public Pulse(User user, String rate) {
        this.user = user;
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

}
