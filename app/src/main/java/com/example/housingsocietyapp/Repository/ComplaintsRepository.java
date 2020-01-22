package com.example.housingsocietyapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.housingsocietyapp.Model.Database.Complaints;
import com.example.housingsocietyapp.Model.Database.ComplaintsDao;
import com.example.housingsocietyapp.Model.Database.HousingDatabase;

import java.util.List;

public class ComplaintsRepository {

    LiveData<List<Complaints>> allComplaints;
    private ComplaintsDao complaintsDao;

    public ComplaintsRepository (Application application){
        HousingDatabase housingDatabase = HousingDatabase.getInstance(application);
        complaintsDao = housingDatabase.complaintsDao();
        allComplaints = complaintsDao.getAllComplaints();
    }

    public void insertNotice(Complaints complaints){
        new InsertNoticeAsyncTask(complaintsDao).execute(complaints);
    }

    public LiveData<List<Complaints>> getAllComplaints(){
        return allComplaints;
    }

    private static class InsertNoticeAsyncTask extends AsyncTask<Complaints, Void,Void> {

        private ComplaintsDao complaintsDao;

        public InsertNoticeAsyncTask(ComplaintsDao complaintsDao) {
            this.complaintsDao = complaintsDao;
        }

        @Override
        protected Void doInBackground(Complaints... complaints) {

            complaintsDao.newComplaint(complaints[0]);
            return null;
        }
    }
}
