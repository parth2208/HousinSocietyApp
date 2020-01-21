package com.example.housingsocietyapp.Model.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VendorsDao {

    @Insert
    void insertVendor(VendorsTable vendorsTable);

    @Update
    void updateVendor(VendorsTable vendorsTable);

    @Query("SELECT address, phone, type, name FROM vendors_table")
    LiveData<List<VendorsTable>> getAllVendors();
}
