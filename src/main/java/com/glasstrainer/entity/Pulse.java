/**
 * 
 */
package com.glasstrainer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Serhat CAN
 *
 */

@Entity
public class Pulse implements  Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private int rate;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}
