package com.health.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="blood_glucose",catalog="health")
public class BloodGlucose implements Serializable{
	private int id;
	private float value;
	private int period;
	private Timestamp time;
	private String remark;
	private int userId;
	@Column(name="user_id")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public BloodGlucose() {
		super();
	}
	public BloodGlucose(int period,float value,Timestamp time, String remark,int userId) {
		super();
		this.value = value;
		this.period = period;
		this.time = time;
		this.remark = remark;
		this.userId = userId;
	}
	public BloodGlucose(int id, int period,float value, Timestamp time, String remark,int userId) {
		super();
		this.id = id;
		this.value = value;
		this.period = period;
		this.time = time;
		this.remark = remark;
		this.userId = userId;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="value")
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	@Column(name="period")
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	@Column(name="time")
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
