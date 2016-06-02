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
@Table(name="weight",catalog="health")
public class Weight implements Serializable {
	private int id;
	private int value;
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
	public Weight(int value,Timestamp time, String remark,int userId) {
		super();
		this.value = value;
		this.time = time;
		this.remark = remark;
		this.userId = userId;
	}
	public Weight() {
		super();
	}
	public Weight(int id,int value, Timestamp time, String remark,int userId) {
		super();
		this.id = id;
		this.value = value;
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
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
