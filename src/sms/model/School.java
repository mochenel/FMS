package sms.model;

/**
 * @author Freddy on 21/08/2022
 * @project Fees Management System
 **/
public class School {
    private int id;
    private String name;
    private String reg_number;
    private String proprietor;

    public School(){

    }

    public School(int id, String name, String reg_number, String proprietor) {
        this.id = id;
        this.name = name;
        this.reg_number = reg_number;
        this.proprietor = proprietor;
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

	public String getReg_number() {
		return reg_number;
	}

	public void setReg_number(String reg_number) {
		this.reg_number = reg_number;
	}

	public String getProprietor() {
		return proprietor;
	}

	public void setProprietor(String proprietor) {
		this.proprietor = proprietor;
	}


}
