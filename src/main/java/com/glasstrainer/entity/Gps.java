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

    @Column(nullable = false, precision = 6)
    private Double latitude;

    @Column(nullable = false, precision = 6)
    private Double longitude;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable = false)
    private Date created;

    public Gps() {
    }

    public Gps(User user, Double latitude, Double longitude) {
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Gps(User user, Double latitude, Double longitude, Time gpsTime) {
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gpsTime = gpsTime;
    }

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
