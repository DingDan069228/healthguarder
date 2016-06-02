package com.health.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="hospital_report",catalog="health")
public class HospitalReport implements Serializable {
	private int id;
	private String hospital;
	private String time;
	private String remark;
	private String url;
	private int userId;
	public HospitalReport() {
		super();
	}
	public HospitalReport(String hospital, String time, String remark,
			String url, int userId) {
		super();
		this.hospital = hospital;
		this.time = time;
		this.remark = remark;
		this.url = url;
		this.userId = userId;
	}
	public HospitalReport(int id, String hospital, String time, String remark,
			String url, int userId) {
		super();
		this.id = id;
		this.hospital = hospital;
		this.time = time;
		this.remark = remark;
		this.url = url;
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
	@Column(name="hospital")
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	@Column(name="time")
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="user_id")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
