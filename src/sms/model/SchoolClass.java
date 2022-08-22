package sms.model;

/**
 * @author Freddy on 21/08/2022
 * @project Fees Management System
 **/
public class SchoolClass {

    private int id;
    private String name;
    private int capacity;
    private String AccademicYear;
	public SchoolClass(int id, String name, int capacity, String accademicYear) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		AccademicYear = accademicYear;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getAccademicYear() {
		return AccademicYear;
	}
	public void setAccademicYear(String accademicYear) {
		AccademicYear = accademicYear;
	}


}
