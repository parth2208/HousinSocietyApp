package com.example.housingsocietyapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.housingsocietyapp.Model.LocalModel.UserAccountInfo;
import com.example.housingsocietyapp.Repository.ProfileRepository;
import com.google.firebase.database.DataSnapshot;

public class ProfileViewModel extends AndroidViewModel {
    private static final String TAG = "ProfileViewModel";

    private ProfileRepository profileRepository;
    private LiveData<UserAccountInfo> accountInfoLiveData;
    private DataSnapshot dataSnapshot1;

    public ProfileViewModel(@NonNull Application application) {
        super(application);

        profileRepository = new ProfileRepository(application);
        accountInfoLiveData = profileRepository.userAccountInfoLiveData(dataSnapshot1);
    }

    public LiveData<UserAccountInfo> getAccountInfoLiveData(DataSnapshot dataSnapshot){
        dataSnapshot1=dataSnapshot;
        return accountInfoLiveData;
    }
}
