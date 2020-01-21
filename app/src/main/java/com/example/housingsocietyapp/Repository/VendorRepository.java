package com.example.housingsocietyapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.housingsocietyapp.Model.Database.HousingDatabase;
import com.example.housingsocietyapp.Model.Database.VendorsDao;
import com.example.housingsocietyapp.Model.Database.VendorsTable;

import java.util.List;

public class VendorRepository {

    private VendorsDao vendorsDao;
    private LiveData<List<VendorsTable>> getAllVendor;

    public VendorRepository(Application application){

        HousingDatabase housingDatabase = HousingDatabase.getInstance(application);
        vendorsDao = housingDatabase.vendorsDao();
        getAllVendor = vendorsDao.getAllVendors();
    }

    public void insertVendor(VendorsTable vendorsTable){
        new InsertVendorAsyncTask(vendorsDao).execute(vendorsTable);
    }

    public void updateVendor(VendorsTable vendorsTable){
        new UpdateVendorAsyncTask(vendorsDao).execute(vendorsTable);
    }

    public LiveData<List<VendorsTable>> getAllVendor(){
        return getAllVendor;
    }

    private static class InsertVendorAsyncTask extends AsyncTask<VendorsTable, Void,Void> {
        private VendorsDao vendorsDao;

        public InsertVendorAsyncTask(VendorsDao vendorsDao) {
            this.vendorsDao = vendorsDao;
        }

        @Override
        protected Void doInBackground(VendorsTable... vendorsTables) {

            vendorsDao.insertVendor(vendorsTables[0]);
            return null;
        }
    }

    private static class UpdateVendorAsyncTask extends AsyncTask<VendorsTable, Void,Void> {
        private VendorsDao vendorsDao;

        public UpdateVendorAsyncTask(VendorsDao vendorsDao) {
            this.vendorsDao = vendorsDao;
        }

        @Override
        protected Void doInBackground(VendorsTable... vendorsTables) {

            vendorsDao.updateVendor(vendorsTables[0]);
            return null;
        }
    }
}
