/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcelTracking.dao;
import java.sql.*;
import parcelTracking.bean.User;
import parcelTracking.util.DBConnection;

/**
 *
 * @author ACER
 */
public class ProfileDAO {

    public User getUser(int userID){

        User user = null;

        try{

            Connection con = DBConnection.getConnection();

            String sql="SELECT * FROM users WHERE userID=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setInt(1,userID);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                user=new User();

                user.setUserID(rs.getInt("userID"));
                user.setFullName(rs.getString("fullName"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return user;

    }

}

