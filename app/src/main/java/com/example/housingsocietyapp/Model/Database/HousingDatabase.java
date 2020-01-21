package com.example.housingsocietyapp.Model.Database;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.housingsocietyapp.R;
import java.text.DateFormat;
import java.util.Date;

@Database(entities = {NoticeTable.class,VendorsTable.class}, version = 1,exportSchema = false)
public abstract class HousingDatabase extends RoomDatabase {

    private static HousingDatabase instance;

    public abstract NoticeDao noticeDao();
    public abstract VendorsDao vendorsDao();

    public static synchronized HousingDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HousingDatabase.class, "housing_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();
            new PopulateVendorAsyncTask(instance).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoticeDao noticeDao;

        public PopulateDbAsyncTask(HousingDatabase database) {
            noticeDao = database.noticeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String currentTime = DateFormat.getDateInstance().format(new Date());

            noticeDao.newNotice(new NoticeTable(Integer.toString(R.string.notice1),currentTime));

            noticeDao.newNotice(new NoticeTable(Integer.toString(R.string.notice2),currentTime));

            noticeDao.newNotice(new NoticeTable(Integer.toString(R.string.notice3),currentTime));

            return null;
        }
    }

    public static class PopulateVendorAsyncTask extends AsyncTask<Void,Void,Void>{

        private VendorsDao vendorsDao;

        public PopulateVendorAsyncTask(HousingDatabase db) {
            vendorsDao = db.vendorsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            vendorsDao.insertVendor(new VendorsTable("81226324468","John Doe", "Mumbai,India","Gas"));
            vendorsDao.insertVendor(new VendorsTable("81226324468","Tompson Doe", "Mumbai,India","Plumber"));
            vendorsDao.insertVendor(new VendorsTable("81226324468","Stacy Henderson", "Mumbai,India","Electrician"));
            vendorsDao.insertVendor(new VendorsTable("81226324468","Loren Freemann", "Mumbai,India","Water"));
            vendorsDao.insertVendor(new VendorsTable("81226324468","John Doe", "Mumbai,India","Grocery"));
            vendorsDao.insertVendor(new VendorsTable("81226324468","John Doe", "Mumbai,India","Gas"));

            return null;
        }
    }

}
