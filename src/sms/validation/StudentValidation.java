package sms.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sms.db.DBConnection;
import sms.model.Student;

public class StudentValidation {

	public static void isEmailValid(Student student, boolean isOperationUpdate) throws Exception {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(student.getEmail());
		if (!matcher.find()) {
			throw new Exception("Invalid Email");
		} else {
			String sql = "SELECT * FROM sms_students WHERE email = ? ";
			if (isOperationUpdate)
				sql = "SELECT * FROM sms_students WHERE email = ?  AND id <>  " + student.getId();
			Connection conn = DBConnection.getDBConnection().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setObject(1, student.getEmail());
			ResultSet rst = stm.executeQuery();
			if (rst.next())
				throw new Exception("Email is taken");
		}

	}

	public static void isNameValid(Student student) throws Exception {
		if (student.getFirst_name().isBlank() || student.getLast_name().isBlank()) {
			throw new Exception("Name (First and Last Names) is required");
		}

	}

	public static void isPhoneNumberValid(Student student, boolean isOperationUpdate) throws Exception {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(student.getPhone_number());
		if (!matcher.find()) {
			throw new Exception("Invalid Phone Number");
		} else {
			String sql = "SELECT * FROM sms_students WHERE phone_number = ? ";
			if (isOperationUpdate)
				sql = "SELECT * FROM sms_students WHERE phone_number = ?  AND id <>  " + student.getId();
			Connection conn = DBConnection.getDBConnection().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setObject(1, student.getPhone_number());
			ResultSet rst = stm.executeQuery();
			if (rst.next())
				throw new Exception("Phone Number is taken");
		}

	}
}
