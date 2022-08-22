package sms.model;

import java.util.Date;

/**
 * @author Freddy on 21/08/2022
 * @project Fees Management System
 **/
public class Student {

    private int id;
    private int class_id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;
    private Date date_of_birth;
    private String address_city;
    private String address_street_and_house_no;
    private String address_region;
    private String address_subregion;
    private String address_country;


	public Student(int id, int class_id, String first_name, String last_name, String phone_number, String email,
			Date date_of_birth, String address_city, String address_street_and_house_no, String address_region,
			String address_subregion, String address_country) {
		super();
		this.id = id;
		this.class_id = class_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone_number = phone_number;
		this.email = email;
		this.date_of_birth = date_of_birth;
		this.address_city = address_city;
		this.address_street_and_house_no = address_street_and_house_no;
		this.address_region = address_region;
		this.address_subregion = address_subregion;
		this.address_country = address_country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getAddress_city() {
		return address_city;
	}
	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}
	public String getAddress_street_and_house_no() {
		return address_street_and_house_no;
	}
	public void setAddress_street_and_house_no(String address_street_and_house_no) {
		this.address_street_and_house_no = address_street_and_house_no;
	}
	public String getAddress_region() {
		return address_region;
	}
	public void setAddress_region(String address_region) {
		this.address_region = address_region;
	}
	public String getAddress_subregion() {
		return address_subregion;
	}
	public void setAddress_subregion(String address_subregion) {
		this.address_subregion = address_subregion;
	}
	public String getAddress_country() {
		return address_country;
	}
	public void setAddress_country(String address_country) {
		this.address_country = address_country;
	}




}
