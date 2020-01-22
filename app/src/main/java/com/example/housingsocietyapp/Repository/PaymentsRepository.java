package com.example.housingsocietyapp.Repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.housingsocietyapp.Model.Database.HousingDatabase;
import com.example.housingsocietyapp.Model.Database.Payments;
import com.example.housingsocietyapp.Model.Database.PaymentsDao;
import java.util.List;

public class PaymentsRepository {

    private PaymentsDao paymentsDao;
    LiveData<List<Payments>> allPayments;

    public PaymentsRepository(Application application){
        HousingDatabase housingDatabase = HousingDatabase.getInstance(application);
        paymentsDao = housingDatabase.paymentsDao();
        allPayments = paymentsDao.getAllPayments();
    }

    public void insertPayments(Payments payments){
        new InsertPaymentsAsyncTask(paymentsDao).execute(payments);
    }

    public LiveData<List<Payments>> getAllPayments(){
        return getAllPayments();
    }

    private static class InsertPaymentsAsyncTask extends AsyncTask<Payments, Void,Void> {
        private PaymentsDao vendorsDao;

        public InsertPaymentsAsyncTask(PaymentsDao vendorsDao) {
            this.vendorsDao = vendorsDao;
        }

        @Override
        protected Void doInBackground(Payments... payments) {

            vendorsDao.addPayment(payments[0]);
            return null;
        }
    }
}
