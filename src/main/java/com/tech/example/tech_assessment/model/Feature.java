package com.tech.example.tech_assessment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "features")
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int id;
	
	@Column(name = "featureName")
	private String featureName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "canAccess")
	private boolean enable;
	
	public Feature() {

	}

	public Feature(int id, String featureName, String email, boolean enable) {
		super();
		this.id = id;
		this.featureName = featureName;
		this.email = email;
		this.enable = enable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "Feature [id=" + id + ", featureName=" + featureName + ", email=" + email + ", enable=" + enable + "]";
	}

}
