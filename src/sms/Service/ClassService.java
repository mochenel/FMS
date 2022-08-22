package sms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sms.db.DBConnection;
import sms.model.SchoolClass;


/**
 * @author Freddy on 21/08/2022
 * @project Fees Management System
 **/
public class ClassService {

    public static ArrayList<SchoolClass> finAllClasses() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery("Select * From sms_classes");

        ArrayList<SchoolClass>classes=new ArrayList<>();
        while(rst.next()){
            //SchoolClass grade;
            //grade = new SchoolClass(rst.getString("grade"));
        	classes.add(new SchoolClass(rst.getInt("id"), rst.getString("name"), rst.getInt("capacity"), rst.getString("academic_year")));
        }
        return classes;
    }

    public static int addClass(SchoolClass schoolClass) throws ClassNotFoundException, SQLException{
		String SQL = "INSERT INTO sms_classes VALUES(?,?,?,?)";
		Connection conn = DBConnection.getDBConnection().getConnection();
		PreparedStatement stm = conn.prepareStatement(SQL);
		stm.setObject(1, null);
		stm.setObject(2, schoolClass.getName());
		stm.setObject(3, schoolClass.getCapacity());
		stm.setObject(4, schoolClass.getAccademicYear());

		return stm.executeUpdate();
    }
    public static int updateClass(SchoolClass schoolClass) throws ClassNotFoundException, SQLException{
        String SQL = "UPDATE sms_classes SET name= ? ,capacity= ? ,academic_year= ? WHERE id= ? ";
		Connection conn = DBConnection.getDBConnection().getConnection();
		PreparedStatement stm = conn.prepareStatement(SQL);
		stm.setObject(1, schoolClass.getName());
		stm.setObject(2, schoolClass.getCapacity());
		stm.setObject(3, schoolClass.getAccademicYear());
		stm.setObject(4, schoolClass.getId());
		
		return stm.executeUpdate();
    }

}
