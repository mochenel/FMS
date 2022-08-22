package sms.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sms.db.DBConnection;
import sms.model.School;

/**
 * @author Freddy on 21/08/2022
 * @project Fees Management System
 **/
public class SchoolService {

    public static School schoolInfo() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM sms_schools";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            School s=new School(rst.getInt("id"),
            		rst.getString("name"),
            		rst.getString("reg_number"),
                    rst.getString("proprietor"));

            return s;
        }
        return null;
    }

    public static int AddDetails(School school)throws ClassNotFoundException,SQLException {
        String SQL="INSERT INTO sms_schools VALUES(?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1,null);
        stm.setObject(2, school.getName());
        stm.setObject(3, school.getReg_number());
        stm.setObject(4, school.getProprietor());

        return  stm.executeUpdate();
    }

    public static int updateInfo(School school) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE sms_schools SET name= ? ,reg_number= ? ,proprietor= ? WHERE id= ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, school.getName());
        stm.setObject(2, school.getReg_number());
        stm.setObject(3, school.getProprietor());
        stm.setObject(4, school.getId());

        return  stm.executeUpdate();
    }
}
