package com.health.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="concern",catalog="health")
public class Concern implements Serializable{
	private Integer id;
	private Integer userId;
	private Integer familyId;
	private Integer type;
	private Integer mutual;
	private Integer status;
	private String time;
	public Concern() {
		super();
	}
	public Concern(Integer userId, Integer familyId, Integer type,
			Integer mutual, Integer status, String time) {
		super();
		this.userId = userId;
		this.familyId = familyId;
		this.type = type;
		this.mutual = mutual;
		this.status = status;
		this.time = time;
	}
	public Concern(Integer id, Integer userId, Integer familyId, Integer type,
			Integer mutual, Integer status, String time) {
		super();
		this.id = id;
		this.userId = userId;
		this.familyId = familyId;
		this.type = type;
		this.mutual = mutual;
		this.status = status;
		this.time = time;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name="friend_id")
	public Integer getFamilyId() {
		return familyId;
	}
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="mutual")
	public Integer getMutual() {
		return mutual;
	}
	public void setMutual(Integer mutual) {
		this.mutual = mutual;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="time")
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
