package com.example.housingsocietyapp.Model.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PaymentsDao {

    @Insert
    void addPayment(Payments payments);

    @Query("SELECT paymentAmount, paymentId,paymentTimeStamp ,paymentCycle,billType FROM payments_table")
    LiveData<List<Payments>> getAllPayments;
}
