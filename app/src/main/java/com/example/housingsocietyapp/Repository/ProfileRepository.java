package com.example.housingsocietyapp.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.housingsocietyapp.Model.User;
import com.example.housingsocietyapp.Model.UserAccountInfo;
import com.example.housingsocietyapp.Model.UserSettings;
import com.example.housingsocietyapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileRepository {
    private static final String TAG = "ProfileRepository";

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myref;
    private String userID;
    private LiveData<UserAccountInfo> accountInfoLiveData;

    private Application application;

    public ProfileRepository(Application application){

        this.application = application;
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();

        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public void updateUserSettings(Application application, String display_Name, final long mobile_no){

        new UpdateAsyncTask(application,userID,display_Name,myref,mobile_no).execute();
    }

    public void addNewUser(Application application, String username, String email, String display_name, String display_photo) {

        new AddUserAsyncTask(application,username,email,userID,myref,display_name,display_photo).execute();
    }

    public void changeUsername(String username){

        new ChangeUserNameAsyncTask(application,myref,username,userID).execute();
    }

    public LiveData<UserAccountInfo> userAccountInfoLiveData(DataSnapshot dataSnapshot){
        accountInfoLiveData = userAccountInfo(dataSnapshot);
        return accountInfoLiveData;
    }

    /**
     * Get the user settings info from the database using datasnapshot
     *     for the users that are using the app
     *     Into the app UI.
     */

//    private static class AccountInfoAsyncTask extends AsyncTask<Void, Void, UserAccountInfo>{
//
//        DataSnapshot dataSnapshot;
//        String userID;
//        Application application;
//
//        private AccountInfoAsyncTask(DataSnapshot dataSnapshot, Application application,String userID){
//            this.application = application;
//            this.dataSnapshot = dataSnapshot;
//            this.userID = userID;
//        }
//
//        @Override
//        protected UserAccountInfo doInBackground(Void... voids) {
//                Log.d(TAG, "getUserInfo: getting user general Info");
//
//                UserSettings settings = new UserSettings();
//                User user = new User();
//
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//
//                    //This if will work for the User_Account_Setting node particularly
//                    if (dataSnapshot1.getKey().equals(application.getString(R.string.db_user_settings))){
//                        Log.d(TAG, "getuserInfo: getting information from firebase" + dataSnapshot1);
//
//
//                        try {
//
//                            settings.setDisplay_photo(
//                                    dataSnapshot1.child(userID)
//                                            .getValue(UserSettings.class)
//                                            .getDisplay_photo()
//                            );
//                        }catch (NullPointerException e){
//                            Log.e(TAG, "getuserInfo: NullPointerException"+ e );
//                        }
//                    }
//
//                    //This if will work for the User node particularly
//                    if (dataSnapshot1.getKey().equals(application.getString(R.string.db_user))) {
//                        Log.d(TAG, "getuserInfo: " + dataSnapshot1);
//
//                        user.setUsername(
//                                dataSnapshot1.child(userID)
//                                        .getValue(User.class)
//                                        .getUsername()
//                        );
//
//                        user.setEmail(
//                                dataSnapshot1.child(userID)
//                                        .getValue(User.class)
//                                        .getEmail()
//                        );
//
//                        user.setMobile_no(
//                                dataSnapshot1.child(userID)
//                                        .getValue(User.class)
//                                        .getMobile_no()
//                        );
//
//                        user.setUser_id(
//                                dataSnapshot1.child(userID)
//                                        .getValue(User.class)
//                                        .getUser_id()
//                        );
//                        Log.d(TAG, "getUserInfo: got the info from user node" + user.toString());
//                    }
//                }
//                return new UserAccountInfo(user,settings);
//        }
//    }

    private static class AddUserAsyncTask extends AsyncTask<Void,Void,Void>{

        Application application;
        String username;
        String email;
        String userID;
        DatabaseReference myref;
        String display_name;
        String display_photo;

        public AddUserAsyncTask(Application application, String username, String email, String userID, DatabaseReference myref, String display_name, String display_photo) {
            this.application = application;
            this.username = username;
            this.email = email;
            this.userID = userID;
            this.myref = myref;
            this.display_name = display_name;
            this.display_photo = display_photo;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //This will add the values of the child in the User node
            User user = new User(
                    username,
                    email,
                    userID,
                    1);

            myref.child(application.getString(R.string.db_user))
                    .child(userID).setValue(user);

            //This will add the values of the child in the UserSettings node

            UserSettings userSettings = new UserSettings(
                    display_name,
                    display_photo);

            myref.child(application.getString(R.string.db_user_settings))
                    .child(userID)
                    .setValue(userSettings);

            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Void, Void, Void>{

        Application application;
        String userID;
        String display_Name;
        DatabaseReference myref;
        final long mobile_no;

        public UpdateAsyncTask(Application application, String userID, String display_Name, DatabaseReference myref, long mobile_no) {
            this.application = application;
            this.userID = userID;
            this.display_Name = display_Name;
            this.myref = myref;
            this.mobile_no = mobile_no;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d(TAG, "changeUsername: Updating  "+ display_Name);
            if (display_Name!= null) {
                myref.child(application.getString(R.string.db_user_settings)).child(userID)
                        .child(application.getString(R.string.field_dispalyName)).setValue(display_Name);
            }

            if (mobile_no!= 0) {
                myref.child(application.getString(R.string.db_user_settings)).child(userID)
                        .child(application.getString(R.string.field_mobileNo)).setValue(mobile_no);
            }

            return null;
        }
    }

    private static class ChangeUserNameAsyncTask extends AsyncTask<Void,Void,Void>{

        Application application;
        DatabaseReference myref;
        String username;
        String userID;

        public ChangeUserNameAsyncTask(Application application, DatabaseReference myref, String username, String userID) {
            this.application = application;
            this.myref = myref;
            this.username = username;
            this.userID = userID;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d(TAG, "changeUsername: Changing username "+ username);

            myref.child(application.getString(R.string.db_user)).child(userID)
                    .child(application.getString(R.string.field_username)).setValue(username);

            myref.child(application.getString(R.string.db_user_settings)).child(userID)
                    .child(application.getString(R.string.field_username)).setValue(username);

            return null;
        }
    }


    public UserAccountInfo userAccountInfo(DataSnapshot dataSnapshot){
        Log.d(TAG, "getUserInfo: getting user general Info");

        UserSettings settings = new UserSettings();
        User user = new User();

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

            //This if will work for the User_Account_Setting node particularly
            if (dataSnapshot1.getKey().equals(application.getString(R.string.db_user_settings))){
                Log.d(TAG, "getuserInfo: getting information from firebase" + dataSnapshot1);

                try {

                    settings.setDisplay_photo(
                            dataSnapshot1.child(userID)
                                    .getValue(UserSettings.class)
                                    .getDisplay_photo()
                    );
                }catch (NullPointerException e){
                    Log.e(TAG, "getuserInfo: NullPointerException"+ e );
                }
            }

            //This if will work for the User node particularly
            if (dataSnapshot1.getKey().equals(application.getString(R.string.db_user))) {
                Log.d(TAG, "getuserInfo: " + dataSnapshot1);

                user.setUsername(
                        dataSnapshot1.child(userID)
                                .getValue(User.class)
                                .getUsername()
                );

                user.setEmail(
                        dataSnapshot1.child(userID)
                                .getValue(User.class)
                                .getEmail()
                );

                user.setMobile_no(
                        dataSnapshot1.child(userID)
                                .getValue(User.class)
                                .getMobile_no()
                );

                user.setUser_id(
                        dataSnapshot1.child(userID)
                                .getValue(User.class)
                                .getUser_id()
                );
                Log.d(TAG, "getUserInfo: got the info from user node" + user.toString());
            }
        }
        return new UserAccountInfo(user,settings);
    }
}
