/**
 * 
 */
package com.ss.usermsvc.entity;

import javax.persistence.*;

/**
 * @author Colin Bradshaw
 *
 */
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	private Integer role_ID;
	private String given_Name;
	private String family_Name;
	private String username;
	private String email;
	private String password;
	private String phone;
	private String roles;

	public User(){

	}

	public User(int id, int role_ID, String fName, String lName, String username, String email, String pass, String phone) {
		this.id = id;
		this.role_ID = role_ID;
		this.given_Name = fName;
		this.family_Name = lName;
		this.username = username;
		this.email = email;
		this.password = pass;
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", role_ID=" + role_ID + ", given_Name=" + given_Name + ", family_Name=" + family_Name
				+ ", username=" + username + ", email=" + email + ", password=" + password + ", phone=" + phone + ", roles=" + roles + "]";
	}

	public Object[] toArray(){
		return new Object[]{id, role_ID, given_Name, family_Name, username, email, password, phone};
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRole_ID() {
		return role_ID;
	}
	public void setRole_ID(Integer role_ID) {
		this.role_ID = role_ID;
	}
	public String getGiven_Name() {
		return given_Name;
	}
	public void setGiven_Name(String given_Name) {
		this.given_Name = given_Name;
	}
	public String getFamily_Name() {
		return family_Name;
	}
	public void setFamily_Name(String family_Name) {
		this.family_Name = family_Name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
