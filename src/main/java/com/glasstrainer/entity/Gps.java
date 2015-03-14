/**
 *
 */
package com.glasstrainer.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @author Serhat CAN
 */

@Entity
public class Gps implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /*
     * This is the current time coming from GPS
     */
    private Time gpsTime;

    @Column(length = 10)
    private Double xCoordinate;

    private Double yCoordinate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable = false)
    private Date created;


    // Getters and Setters

    public Date getTime() {
        return created;
    }

    @PrePersist
    protected void setTime() {
        created = new Date();
    }

    public Time getGpsTime() {
        return gpsTime;
    }

    public void setTime(Time gpsTime) {
        this.gpsTime = gpsTime;
    }

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

}
