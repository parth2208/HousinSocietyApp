package com.example.housingsocietyapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.housingsocietyapp.Model.LocalModel.UserAccountInfo;
import com.example.housingsocietyapp.Repository.ProfileRepository;
import com.google.firebase.database.DataSnapshot;

public class ProfileViewModel extends AndroidViewModel {
    private static final String TAG = "ProfileViewModel";

    private ProfileRepository profileRepository;
    private MutableLiveData<UserAccountInfo> accountInfoLiveData;

    public ProfileViewModel(@NonNull Application application) {
        super(application);

        profileRepository = new ProfileRepository(application);
    }

    public MutableLiveData<UserAccountInfo> getAccountInfoLiveData(DataSnapshot dataSnapshot){
        accountInfoLiveData = profileRepository.userAccountInfoLiveData(dataSnapshot);
        return accountInfoLiveData;
    }
}
