package sms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sms.db.DBConnection;
import sms.model.Student;

/**
 * @author Freddy on 21/08/2022
 * @project Fees Management System
 **/
public class StudentService {

	public static int AddStudent(Student student) throws ClassNotFoundException, SQLException {
		String SQL = "INSERT INTO sms_students VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DBConnection.getDBConnection().getConnection();
		PreparedStatement stm = conn.prepareStatement(SQL);
		stm.setObject(1, null);
		stm.setObject(2, student.getClass_id());
		stm.setObject(3, student.getFirst_name());
		stm.setObject(4, student.getLast_name());
		stm.setObject(5, student.getPhone_number());
		stm.setObject(6, student.getEmail());
		stm.setObject(7, student.getDate_of_birth());
		stm.setObject(8, student.getAddress_city());
		stm.setObject(9, student.getAddress_street_and_house_no());
		stm.setObject(10, student.getAddress_region());
		stm.setObject(11, student.getAddress_subregion());
		stm.setObject(12, student.getAddress_country());

		return stm.executeUpdate();
	}

	public static Student searchStudent(Integer id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM sms_students WHERE id = ? ";
		Connection conn = DBConnection.getDBConnection().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setObject(1, id);
		ResultSet rst = stm.executeQuery();
		if (rst.next())
			return new Student(rst.getInt("id"), rst.getInt("class_id"), rst.getString("first_name"),
					rst.getString("last_name"), rst.getString("phone_number"), rst.getString("email"),
					rst.getDate("date_of_birth"), rst.getString("address_city"),
					rst.getString("address_street_and_house_no"), rst.getString("address_region"),
					rst.getString("address_subregion"), rst.getString("address_country"));
		
			return null;
	}
	public static ArrayList<Student> findAllStudnts() throws ClassNotFoundException, SQLException {
		
		ArrayList<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM sms_students";
		Connection conn = DBConnection.getDBConnection().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet rst = stm.executeQuery();
		while (rst.next())
			students.add(new Student(rst.getInt("id"), rst.getInt("class_id"), rst.getString("first_name"),
					rst.getString("last_name"), rst.getString("phone_number"), rst.getString("email"),
					rst.getDate("date_of_birth"), rst.getString("address_city"),
					rst.getString("address_street_and_house_no"), rst.getString("address_region"),
					rst.getString("address_subregion"), rst.getString("address_country")));
		return students;
		
	}
	public static ArrayList<Student> findStudntsByClassId(int class_id) throws ClassNotFoundException, SQLException {
		
		ArrayList<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM sms_students WHERE class_id = ? ";
		Connection conn = DBConnection.getDBConnection().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setObject(1, class_id);
		ResultSet rst = stm.executeQuery();
		while (rst.next())
			students.add(new Student(rst.getInt("id"), rst.getInt("class_id"), rst.getString("first_name"),
					rst.getString("last_name"), rst.getString("phone_number"), rst.getString("email"),
					rst.getDate("date_of_birth"), rst.getString("address_city"),
					rst.getString("address_street_and_house_no"), rst.getString("address_region"),
					rst.getString("address_subregion"), rst.getString("address_country")));
		return students;
		
	}

	public static int deleteStudent(String id) throws ClassNotFoundException, SQLException {

		String sql = "DELETE FROM sms_students WHERE id ='" + id + "'";
		Connection conn = DBConnection.getDBConnection().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);

		return stm.executeUpdate();
	}

	public static int updateStudent(Student student) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE sms_students SET class_id= ? ,first_name= ? ,last_name= ? ,phone_number= ? ,email= ? ,date_of_birth= ? ,"
				+ "address_city=? ,address_street_and_house_no= ? ,address_region= ? ,address_subregion= ? ,address_country=? WHERE id= ?";
		Connection conn = DBConnection.getDBConnection().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setObject(1, student.getClass_id());
		stm.setObject(2, student.getFirst_name());
		stm.setObject(3, student.getLast_name());
		stm.setObject(4, student.getPhone_number());
		stm.setObject(5, student.getEmail());
		stm.setObject(6, student.getDate_of_birth());
		stm.setObject(7, student.getAddress_city());
		stm.setObject(8, student.getAddress_street_and_house_no());
		stm.setObject(9, student.getAddress_region());
		stm.setObject(10, student.getAddress_subregion());
		stm.setObject(11, student.getAddress_country());
		stm.setObject(12, student.getId());

		return stm.executeUpdate();
	}

}
