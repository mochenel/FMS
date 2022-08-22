package sms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sms.db.DBConnection;

/**
 * @author Freddy on 21/08/2022
 * @project Fees Management System
 **/
public class LoginService {


    public boolean Login(String userType, String username, String password) throws SQLException, ClassNotFoundException {
    	
        String SQL;
        if(userType == "teacher") 
        	SQL = "SELECT * FROM sms_teachers WHERE username=? AND password=?";
        else if(userType == "admin")
        	SQL = "SELECT * FROM sms_admins WHERE username=? AND password=?";
        else
        	return false;
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1, username);
        stm.setObject(2, password);
        ResultSet rst = stm.executeQuery();
        return rst.next();


}
}
