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
public class Acceleration implements Serializable {

    private static final long serialVersionUID = 1L;

    // cx:-0.023-cy:0.056-cz:1.001
    // cx:-0.024-cy:0.049-cz:0.998

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private String cx;
    private String cy;
    private String cz;

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

    public Acceleration() {

    }

    public Acceleration(String cx, String cy, String cz) {
        this.cx = cx;
        this.cy = cy;
        this.cz = cz;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public String getCy() {
        return cy;
    }

    public void setCy(String cy) {
        this.cy = cy;
    }

    public String getCz() {
        return cz;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getResultantAcceleration() {
        Double cx =  Double.parseDouble(getCx());
        Double cy =  Double.parseDouble(getCy());
        Double cz =  Double.parseDouble(getCz());

        Double resultantAcc = cx*cx + cy*cy + cz*cz;
        resultantAcc = Math.sqrt(resultantAcc);

        return resultantAcc;
    }

}
