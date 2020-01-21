package com.example.housingsocietyapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.housingsocietyapp.Model.Database.HousingDatabase;
import com.example.housingsocietyapp.Model.Database.NoticeDao;
import com.example.housingsocietyapp.Model.Database.NoticeTable;

import java.util.List;

public class NoticeRepository {

    private NoticeDao noticeDao;
    private LiveData<List<NoticeTable>> allNotice;

    public NoticeRepository(Application application){

        HousingDatabase housingDatabase = HousingDatabase.getInstance(application);
        noticeDao = housingDatabase.noticeDao();
        allNotice = noticeDao.getAllNotice();
    }

    public void insertNotice(NoticeTable noticeTable){

        new InsertNoticeAsyncTask(noticeDao).execute(noticeTable);

    }

    public LiveData<List<NoticeTable>> getAllNotice(){
        return allNotice;
    }

    private static class InsertNoticeAsyncTask extends AsyncTask<NoticeTable, Void,Void>{

        private NoticeDao noticeDao;

        public InsertNoticeAsyncTask(NoticeDao noticeDao) {
            this.noticeDao = noticeDao;
        }

        @Override
        protected Void doInBackground(NoticeTable... noticeTables) {

            noticeDao.newNotice(noticeTables[0]);
            return null;
        }
    }
}
