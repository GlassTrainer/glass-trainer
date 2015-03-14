/**
 *
 *//*

package com.glasstrainer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

*/
/**
 * @author Serhat CAN
 *
 *//*


@Entity
public class AthleteYedek implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 11, unique = true)
	private Long tcNo;

	@Column(nullable = false, length = 25)
	private String fname;

	@Column(nullable = false, length = 25)
	private String lname;

	@Column(precision = 2)
	private Double weight;

	@Column(length = 3)
	private int size;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTcNo() {
		return tcNo;
	}

	public void setTcNo(Long tcNo) {
		this.tcNo = tcNo;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
*/
