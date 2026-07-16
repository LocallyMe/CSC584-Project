package com.mycompany.parceltracking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackingDAO {
    // Assuming you have a DBConnection utility class in your project
    public TrackingData getTrackingDetails(String trackingNumber) {
        TrackingData data = null;
        String parcelQuery = "SELECT p.parcelID, p.trackingNumber, p.currentStatus, d.estimatedDate " +
                             "FROM parcel p " +
                             "LEFT JOIN delivery d ON p.parcelID = d.parcelID " +
                             "WHERE p.trackingNumber = ?";
                             
        String historyQuery = "SELECT location, updateTime, remark " +
                              "FROM tracking WHERE parcelID = ? ORDER BY updateTime DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psParcel = conn.prepareStatement(parcelQuery)) {
            
            psParcel.setString(1, trackingNumber);
            ResultSet rsParcel = psParcel.executeQuery();

            if (rsParcel.next()) {
                data = new TrackingData();
                int parcelId = rsParcel.getInt("parcelID");
                data.setTrackingNumber(rsParcel.getString("trackingNumber"));
                data.setCurrentStatus(rsParcel.getString("currentStatus"));
                data.setEstimatedDate(rsParcel.getDate("estimatedDate"));

                // Fetch History
                try (PreparedStatement psHistory = conn.prepareStatement(historyQuery)) {
                    psHistory.setInt(1, parcelId);
                    ResultSet rsHistory = psHistory.executeQuery();
                    List<TrackingData.Checkpoint> checkpoints = new ArrayList<>();
                    
                    while (rsHistory.next()) {
                        TrackingData.Checkpoint cp = new TrackingData.Checkpoint();
                        cp.setLocation(rsHistory.getString("location"));
                        cp.setUpdateTime(rsHistory.getTimestamp("updateTime"));
                        cp.setRemark(rsHistory.getString("remark"));
                        checkpoints.add(cp);
                    }
                    data.setCheckpoints(checkpoints);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}