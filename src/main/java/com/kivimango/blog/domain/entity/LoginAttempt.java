package com.kivimango.blog.domain.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blog_login_attempts")
public class LoginAttempt {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String ipAdress;
	
	private Timestamp attemptDate;

	public LoginAttempt(String ipAdress, Timestamp attemptDate) {
		this.ipAdress = ipAdress;
		this.attemptDate = attemptDate;
	}

	public LoginAttempt() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public Timestamp getAttemptDate() {
		return attemptDate;
	}

	public void setAttemptDate(Timestamp attemptDate) {
		this.attemptDate = attemptDate;
	}
	
}
