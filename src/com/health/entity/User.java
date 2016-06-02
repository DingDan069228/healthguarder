package com.health.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.type.TrueFalseType;
//JPA(Java Persistence API),这里使用的均是java自身提供的对象持久化API，而不是hibernate的API，java自身提供的GenerationType有四种
//分别是AUTO，IDENTITY,TABLE和SEQUENCE
//TABLE:使用一个特定的数据库表格来保存主键
//SEQUENCE:根据底层数据库的序列来生成主键，条件是数据库支持序列（ORACLE）
//AUTO:主键由程序控制，默认生成策略
//IDENTITY:主键由数据库自动生成（主要是自动增长型）
@Entity
@Table(name="user",catalog="health")
public class User implements Serializable {
	//private static final GenerationType IDENTITY = null;
	private int id;
	private String userName;
	private String password;
	private String realName;
	private String gender;
	private String birthday;
	private String phoneNumber;
	private String email;
	private int height;
	private int weight;
	private Blob image;
	@Column(name="image")
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="height")
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Column(name="weight")
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	//private Set<User> users = new HashSet<User>();
	public User() {
		super();
	}
	//在hibernate中，当设置了主键生成策略时，向数据库插入的记录是不包含主键值的，如果主键没有默认值，则会报错UncategorizedSQLException
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="userName",nullable=false,unique=true)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="realName")
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public User(int id,String userName, String password, String realName,String gender,
			String birthday, String phoneNumber) {
		super();
		this.id=id;
		this.userName = userName;
		this.password = password;
		this.realName=realName;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		//this.users = users;
	}
	//full constructor
	public User(String userName, String password, String realName,
			String gender, String birthday, String phoneNumber, String email,
			int height, int weight,Blob image) {
		super();
		this.userName = userName;
		this.password = password;
		this.realName = realName;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.height = height;
		this.weight = weight;
		this.image=image;
	}
	public User(String userName, String password, String realName,
			String gender, String birthday, String phoneNumber) {
		super();
		this.userName = userName;
		this.password = password;
		this.realName = realName;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name="birthday")
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "User [birthday=" + birthday + ", email=" + email + ", gender="
				+ gender + ", height=" + height + ", id=" + id + ", image="
				+ image + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", realName=" + realName + ", userName="
				+ userName + ", weight=" + weight + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj); 
	}
	/*@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="id")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}*/
	public static void main(String... args){
		User user = new User("ding", "123456", "ding", null, null, null);
		System.out.println(user.toString());
		//User user2 = new User("admin",null, "Jack", "男", null, null, null, null, null,null);
		System.out.println(user);
	}
}
