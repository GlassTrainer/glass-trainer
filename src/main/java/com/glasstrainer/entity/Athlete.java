/**
 * 
 */
package com.glasstrainer.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Serhat CAN
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Athlete extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	public Athlete() {
	}

/*	public Athlete (String username, String password, String email, String firstname, String surname, Role role, Double weight, Double height) {
		super(username, password, email, firstname, surname, role);
		this.weight = weight;
		this.height = height;
	}*/

	@Column(precision = 2)
	private Double weight;

	@Column(length = 3)
	private Double height;


	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

}
