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
                user.setRole(rs.getString("role"));

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return user;

    }

    public boolean updateProfile(User user) {

        boolean result=false;

        String sql=
        "UPDATE users SET fullName=?, phoneNumber=?, email=? WHERE userID=?";

        try{

        Connection con=DBConnection.getConnection();

        PreparedStatement ps=con.prepareStatement(sql);

        ps.setString(1,user.getFullName());
        ps.setString(2,user.getPhoneNumber());
        ps.setString(3,user.getEmail());
        ps.setInt(4,user.getUserID());

        result=ps.executeUpdate()>0;

        }catch(Exception e){

        e.printStackTrace();

        }

        return result;

        }
    
    public boolean changePassword(int userID,
        String currentPassword,
        String newPassword){

        boolean result=false;

        String sql=
        "UPDATE users SET password=? WHERE userID=? AND password=?";

        try{

        Connection con=DBConnection.getConnection();

        PreparedStatement ps=con.prepareStatement(sql);

        ps.setString(1,newPassword);
        ps.setInt(2,userID);
        ps.setString(3,currentPassword);

        int row = ps.executeUpdate();

        System.out.println("Rows Updated = " + row);

        result = row > 0;

        }catch(Exception e){

        e.printStackTrace();

        }

        return result;

        }
    }


