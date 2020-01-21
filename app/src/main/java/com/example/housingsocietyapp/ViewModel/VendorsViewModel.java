package com.example.housingsocietyapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.housingsocietyapp.Model.Database.VendorsTable;
import com.example.housingsocietyapp.Repository.VendorRepository;

import java.util.List;

public class VendorsViewModel extends AndroidViewModel {

    private static final String TAG = "NoticeViewModel";

    private VendorRepository vendorRepository;
    private LiveData<List<VendorsTable>> listLiveData;

    public VendorsViewModel(@NonNull Application application) {
        super(application);

        vendorRepository = new VendorRepository(application);
        listLiveData = vendorRepository.getAllVendor();
    }

    public void insert(VendorsTable vendorsTable){
        vendorRepository.insertVendor(vendorsTable);
    }

    public LiveData<List<VendorsTable>> getListLiveData(){
        return listLiveData;
    }
}
