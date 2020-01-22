package com.example.housingsocietyapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.housingsocietyapp.Model.Database.Payments;
import com.example.housingsocietyapp.Repository.PaymentsRepository;
import java.util.List;

public class PaymentsViewModel extends AndroidViewModel {
    private static final String TAG = "PaymentsViewModel";

    private PaymentsRepository paymentsRepository;
    private LiveData<List<Payments>> getAllPayments;

    public PaymentsViewModel(@NonNull Application application) {
        super(application);
        paymentsRepository = new PaymentsRepository(application);
        getAllPayments = paymentsRepository.getAllPayments();
    }

    public void insert(Payments payments){
        paymentsRepository.insertPayments(payments);
    }

    public LiveData<List<Payments>> getListLiveData(){
        return getAllPayments;
    }
}
