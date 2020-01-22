package com.example.housingsocietyapp.View.UI.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.housingsocietyapp.Model.LocalModel.UserAccountInfo;
import com.example.housingsocietyapp.R;
import com.example.housingsocietyapp.Utils.BottomNavigationHelper;
import com.example.housingsocietyapp.Utils.LoginActivity;
import com.example.housingsocietyapp.ViewModel.NoticeViewModel;
import com.example.housingsocietyapp.ViewModel.ProfileViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";

    private static final int Activity_Num = 1;

    //FireBase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener AuthListner;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference  databaseReference;
    private ProfileViewModel profileViewModel;
    private UserAccountInfo userAccountInfo;

    private TextView name, userName, userMobile, userEmail;
    private CircleImageView displayPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        setupfirebaseAuth();
        setupBottomNavigationView();
        initWidget();
    }

    public void initWidget(){
        name = findViewById(R.id.profile_name);
        userName = findViewById(R.id.profile_userName);
        userEmail = findViewById(R.id.profile_userEmail);
        displayPhoto = findViewById(R.id.profile_picture);
        userMobile = findViewById(R.id.profile_userMobile);
    }

    public void setUserInfo(UserAccountInfo info){
        this.userAccountInfo = info;
        name.setText(userAccountInfo.getUserSettings().getDisplay_name());
        userName.setText(userAccountInfo.getUser().getUsername());
        userEmail.setText(userAccountInfo.getUser().getEmail());
        userMobile.setText(String.format(Long.toString(userAccountInfo.getUser().getMobile_no())));

    }

    /**
     * BottomNavigationViewSetup
     */

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: Setting up bottom navigation view");

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavBarView);
        BottomNavigationHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationHelper.enableNavigation(ProfileActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Activity_Num);
        menuItem.setChecked(true);
    }

    /**
     * Setting up firebase Auth
     */

    private void checkCurrentUser(FirebaseUser User){
        Log.d(TAG, "checkCurrentUser: checking current user");
        if (User == null) {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void setupfirebaseAuth() {
        Log.d(TAG, "setupfirebaseAuth: SettingUp firebase Authentications");

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        AuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                checkCurrentUser(user);

                if (user != null) {
                    //user is signed in
                    Log.d(TAG, "onAuthStateChanged: Signed In" + user.getUid());
                } else {
                    // User is Signed out
                    Log.d(TAG, "onAuthStateChanged: Signed Out");
                }
            }
        };

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Retrieve data from Firebase.
                profileViewModel.getAccountInfoLiveData(dataSnapshot).observe(ProfileActivity.this, new Observer<UserAccountInfo>() {
                    @Override
                    public void onChanged(UserAccountInfo userAccountInfo) {
                        setUserInfo(userAccountInfo);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(AuthListner);
        checkCurrentUser(mAuth.getCurrentUser());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (AuthListner!= null){
            mAuth.removeAuthStateListener(AuthListner);
        }
    }
}
