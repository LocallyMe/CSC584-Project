/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcelTracking.bean;

/**
 *
 * @author ACER
 */
public class CustomerService {
    
    private int requestID;
    private int userID;
    private String requesterName;
    private String trackingNumber;
    private String parcelOwnerName;
    private String issueType;
    private String priority;
    private String subject;
    private String message;
    private String status;


    public int getRequestID() {
        return requestID;
    }


    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }


    public int getUserID() {
        return userID;
    }


    public void setUserID(int userID) {
        this.userID = userID;
    }


    public String getRequesterName() {
        return requesterName;
    }


    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }


    public String getTrackingNumber() {
        return trackingNumber;
    }


    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }


    public String getParcelOwnerName() {
        return parcelOwnerName;
    }


    public void setParcelOwnerName(String parcelOwnerName) {
        this.parcelOwnerName = parcelOwnerName;
    }


    public String getIssueType() {
        return issueType;
    }


    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }


    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}
