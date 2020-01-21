package com.example.housingsocietyapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.housingsocietyapp.Model.Database.NoticeTable;
import com.example.housingsocietyapp.Repository.NoticeRepository;

import java.util.List;

public class NoticeViewModel extends AndroidViewModel {
    private static final String TAG = "NoticeViewModel";

    private NoticeRepository noticeRepository;
    private LiveData<List<NoticeTable>> listLiveData;

    public NoticeViewModel(@NonNull Application application) {
        super(application);

        noticeRepository = new NoticeRepository(application);
        listLiveData = noticeRepository.getAllNotice();
    }

    public void insert(NoticeTable noticeTable){
        noticeRepository.insertNotice(noticeTable);
    }

    public LiveData<List<NoticeTable>> getListLiveData(){
        return listLiveData;
    }
}
