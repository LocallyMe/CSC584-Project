/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcelTracking.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import parcelTracking.bean.CustomerService;
import parcelTracking.util.DBConnection;
import java.sql.ResultSet;


public class CustomerServiceDAO {



    public boolean submitRequest(CustomerService cs){


        boolean result = false;


        String sql = 
        "INSERT INTO customer_service "
        +"(userID, requesterName, trackingNumber, parcelOwnerName,"
        +"issueType, priority, subject, message)"
        +"VALUES(?,?,?,?,?,?,?,?)";



        try{


            Connection con = DBConnection.getConnection();


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setInt(1, cs.getUserID());

            ps.setString(2, cs.getRequesterName());

            ps.setString(3, cs.getTrackingNumber());

            ps.setString(4, cs.getParcelOwnerName());

            ps.setString(5, cs.getIssueType());

            ps.setString(6, cs.getPriority());

            ps.setString(7, cs.getSubject());

            ps.setString(8, cs.getMessage());



            int row = ps.executeUpdate();



            if(row > 0){

                result = true;

            }



        }
        catch(Exception e){

            e.printStackTrace();

        }



        return result;

    }
    public String getParcelOwner(String trackingNumber){

    String owner = null;

    String sql =
    "SELECT receiverName FROM parcel WHERE trackingNumber=?";

    try{

        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
        con.prepareStatement(sql);

        ps.setString(1, trackingNumber);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            owner = rs.getString("receiverName");

        }

    }catch(Exception e){

        e.printStackTrace();

    }

    return owner;

}

}
