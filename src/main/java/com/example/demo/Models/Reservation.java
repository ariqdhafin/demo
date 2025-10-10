package com.example.demo.Models;

import java.sql.Date;
import java.sql.Time;

public class Reservation {
    private int id;
    private int employeeId;
    private int roomId;
    private java.sql.Date submitDate;
    private String purpose;
    private java.sql.Date reservationDate;
    private java.sql.Time startTime;
    private java.sql.Time endTime;
    private String approvalStatus;
    private int approvedBy;

    public Reservation(int id, int employeeId, int roomId, Date submitDate, String purpose, Date reservationDate,
            Time startTime, Time endTime, String approvalStatus, int approvedBy) {
        this.id = id;
        this.employeeId = employeeId;
        this.roomId = roomId;
        this.submitDate = submitDate;
        this.purpose = purpose;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approvalStatus = approvalStatus;
        this.approvedBy = approvedBy;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public java.sql.Date getSubmitDate() {
        return submitDate;
    }
    public void setSubmitDate(java.sql.Date submitDate) {
        this.submitDate = submitDate;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    public java.sql.Date getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(java.sql.Date reservationDate) {
        this.reservationDate = reservationDate;
    }
    public java.sql.Time getStartTime() {
        return startTime;
    }
    public void setStartTime(java.sql.Time startTime) {
        this.startTime = startTime;
    }
    public java.sql.Time getEndTime() {
        return endTime;
    }
    public void setEndTime(java.sql.Time endTime) {
        this.endTime = endTime;
    }
    public String getApprovalStatus() {
        return approvalStatus;
    }
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    public int getApprovedBy() {
        return approvedBy;
    }
    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }

    
}
