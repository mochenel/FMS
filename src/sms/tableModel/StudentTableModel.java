package sms.tableModel;

import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Freddy on 21/08/2022
 * @project Fees Management System
 **/
public class StudentTableModel {

    private SimpleIntegerProperty  id;
    private SimpleStringProperty  className;
    private SimpleStringProperty  first_name;
    private SimpleStringProperty  last_name;
    private SimpleStringProperty  phone_number;
    private SimpleStringProperty  email;
    private SimpleObjectProperty<Date>  date_of_birth;
    private SimpleStringProperty  address;



	public StudentTableModel(Integer id, String className, String first_name, String last_name, String phone_number, String email,
			Date date_of_birth, String address) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.className = new SimpleStringProperty(className);
		this.first_name = new SimpleStringProperty(first_name);
		this.last_name = new SimpleStringProperty(last_name);
		this.phone_number = new SimpleStringProperty(phone_number);
		this.email = new SimpleStringProperty(email);
		this.date_of_birth = new SimpleObjectProperty<Date>(date_of_birth);
		this.address = new SimpleStringProperty(address);

	}


	public int getId() {
		return id.get();
	}


	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}


	public String getClassName() {
		return className.get();
	}


	public void setClassName(String className) {
		this.className = new SimpleStringProperty(className);
	}


	public String getFirst_name() {
		return first_name.get();
	}


	public void setFirst_name(String first_name) {
		this.first_name = new SimpleStringProperty(first_name);
	}


	public String getLast_name() {
		return last_name.get();
	}


	public void setLast_name(String last_name) {
		this.last_name = new SimpleStringProperty(last_name);
	}


	public String getPhone_number() {
		return phone_number.get();
	}


	public void setPhone_number(String phone_number) {
		this.phone_number = new SimpleStringProperty(phone_number);
	}


	public String getEmail() {
		return email.get();
	}


	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}


	public String getDate_of_birth() {
		return date_of_birth.get().toString();
	}


	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = new SimpleObjectProperty<Date>(date_of_birth);
	}


	public String getAddress() {
		return address.get();
	}


	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}








}
