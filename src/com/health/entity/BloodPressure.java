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
@Table(name="blood_pressure",catalog="health")
public class BloodPressure implements Serializable{
	private int id;
	private int value;
	private Timestamp time;
	private int userId;
	//映射类必须要有一个空的默认构造器
	public BloodPressure(){
		
	}
	public BloodPressure(int id, int value, Timestamp time, int userId) {
		super();
		this.id = id;
		this.value = value;
		this.time = time;
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
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Column(name="time")
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Column(name="user_id")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "BloodPressure [id=" + id + ", time=" + time + ", userId="
				+ userId + ", value=" + value + "]";
	}
	
}
