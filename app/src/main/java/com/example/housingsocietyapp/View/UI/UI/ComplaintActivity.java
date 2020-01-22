package com.example.housingsocietyapp.View.UI.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.ViewModelProviders;

import com.example.housingsocietyapp.Model.Database.Complaints;
import com.example.housingsocietyapp.R;
import com.example.housingsocietyapp.Utils.LoginActivity;
import com.example.housingsocietyapp.ViewModel.ComplaintsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ComplaintActivity extends AppCompatActivity {
    private static final String TAG = "ComplaintActivity";

    private ComplaintsViewModel complaintsViewModel;
    private Complaints complaints;
    EditText complaintTitle, complaintsDescription;
    AppCompatImageButton complaintSubmitButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener AuthListner;

    private String title;
    private String complaintString;
    private String userId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        complaintsViewModel = ViewModelProviders.of(this).get(ComplaintsViewModel.class);

        initWidget();
        setupfirebaseAuth();
        submitComplaints();

    }

    public void initWidget(){
        complaintTitle = findViewById(R.id.edit_complaints_title);
        complaintsDescription = findViewById(R.id.edit_complaints_description);
        complaintSubmitButton = findViewById(R.id.submit_complaint_btn);
    }

    public void submitComplaints(){
        title = complaintTitle.toString();
        complaintString = complaintsDescription.toString();

        complaintSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkinputs(title,complaintString)){
                    complaints = new Complaints(complaintString,userId,title);
                    complaintsViewModel.insert(complaints);
                }
                complaintTitle.setText("");
                complaintsDescription.setText("");
                Toast.makeText(ComplaintActivity.this, "Your complaint is submitted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Required For Fill Entire Forms
    private boolean checkinputs(String title, String complaints) {
        Log.d(TAG, "checkinputs: Checking if input field is filled or not");

        if (title.equals("") || complaints.equals("")){
            Toast.makeText(this, "Please fill all the field.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Setting up firebase Auth
     */
    private void checkCurrentUser(FirebaseUser User){
        Log.d(TAG, "checkCurrentUser: checking current user");
        if (User == null) {
            Intent intent = new Intent(ComplaintActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void setupfirebaseAuth() {
        Log.d(TAG, "setupfirebaseAuth: SettingUp firebase Authentications");

        mAuth = FirebaseAuth.getInstance();
        AuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                checkCurrentUser(user);

                if (user != null) {
                    //user is signed in
                    userId = user.getUid();
                    Log.d(TAG, "onAuthStateChanged: Signed In" + user.getUid());
                } else {
                    // User is Signed out
                    Log.d(TAG, "onAuthStateChanged: Signed Out");
                }
            }
        };
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
