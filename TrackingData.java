package parcelTracking.bean;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

public class TrackingData {
    private String trackingNumber;
    private String currentStatus;
    private Date estimatedDate;
    private List<Checkpoint> checkpoints;

    // Getters and Setters
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }
    public Date getEstimatedDate() { return estimatedDate; }
    public void setEstimatedDate(Date estimatedDate) { this.estimatedDate = estimatedDate; }
    public List<Checkpoint> getCheckpoints() { return checkpoints; }
    public void setCheckpoints(List<Checkpoint> checkpoints) { this.checkpoints = checkpoints; }

    // Inner class for Tracking Checkpoints
    public static class Checkpoint {
        private String location;
        private Timestamp updateTime;
        private String remark;

        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        public Timestamp getUpdateTime() { return updateTime; }
        public void setUpdateTime(Timestamp updateTime) { this.updateTime = updateTime; }
        public String getRemark() { return remark; }
        public void setRemark(String remark) { this.remark = remark; }
    }
}
