package com.example.housingsocietyapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.housingsocietyapp.Model.Database.Complaints;
import com.example.housingsocietyapp.Repository.ComplaintsRepository;

import java.util.List;

public class ComplaintsViewModel extends AndroidViewModel {

    private ComplaintsRepository complaintsRepository;
    private LiveData<List<Complaints>> listLiveData;

    public ComplaintsViewModel(@NonNull Application application) {
        super(application);

        complaintsRepository = new ComplaintsRepository(application);
        listLiveData = complaintsRepository.getAllComplaints();
    }

    public void insert(Complaints complaints){
        complaintsRepository.insertNotice(complaints);
    }

    public LiveData<List<Complaints>> getListLiveData(){
        return listLiveData;
    }
}
