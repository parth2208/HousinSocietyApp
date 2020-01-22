package com.example.housingsocietyapp.Model.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "complaints_table")
public class Complaints {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int complaintId;
    String complaint;
    String title;
    String userId;

    public Complaints(String complaint, String userId, String title) {
        this.title = title;
        this.complaint = complaint;
        this.userId = userId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Complaints{" +
                "complaintId=" + complaintId +
                ", complaint='" + complaint + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
