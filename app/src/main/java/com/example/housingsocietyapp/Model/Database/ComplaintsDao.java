package com.example.housingsocietyapp.Model.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ComplaintsDao {

    @Insert
    void newComplaint(Complaints complaints);

    @Query("SELECT complaint, complaintId FROM complaints_table")
    LiveData<List<Complaints>> getAllComplaints();

}
