package com.example.housingsocietyapp.View.UI.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.housingsocietyapp.Model.LocalModel.HomeRecyclerDataModel;
import com.example.housingsocietyapp.R;
import com.example.housingsocietyapp.Utils.BottomNavigationHelper;
import com.example.housingsocietyapp.Utils.LoginActivity;
import com.example.housingsocietyapp.View.UI.UI.Adapter.HomeRecyclerViewAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.INTERNET;

public class MainActivity extends AppCompatActivity implements HomeRecyclerViewAdapter.ItemListener {
    private static final String TAG = "MainActivity";

    private static final int Activity_Num = 0;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private View view;

    RecyclerView recyclerView;
    ArrayList<HomeRecyclerDataModel> arrayList;

    //FireBase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener AuthListner;
    HomeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupfirebaseAuth();
        setUpRecyclerView();
        setupBottomNavigationView();
    }

    public void setUpRecyclerView(){
        recyclerView = findViewById(R.id.main_recyclerView);
        arrayList = new ArrayList<>();

        arrayList.add(new HomeRecyclerDataModel("Payments", R.drawable.ic_payment_black_24dp, "#673BB7"));
        arrayList.add(new HomeRecyclerDataModel("Vendors", R.drawable.vendors, "#0A9B88"));
        arrayList.add(new HomeRecyclerDataModel("Notice Board", R.drawable.ic_notifications_active_black_24dp, "#3E51B1"));
        arrayList.add(new HomeRecyclerDataModel("Society Rules", R.drawable.ic_rules, "#4BAA50"));
        arrayList.add(new HomeRecyclerDataModel("Complaints", R.drawable.ic_complaints, "#F94336"));

        adapter = new HomeRecyclerViewAdapter(arrayList, this,this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager  gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        permissionRequest();
    }

    /**
     * Setting up firebase Auth
     */
    private void checkCurrentUser(FirebaseUser User){
        Log.d(TAG, "checkCurrentUser: checking current user");
        if (User == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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

     /**
        BottomNavigationBar Setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: Setting up bottom navigation view");

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavBarView);
        BottomNavigationHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationHelper.enableNavigation(MainActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Activity_Num);
        menuItem.setChecked(true);

    }

    @Override
    public void onItemClick(HomeRecyclerDataModel item) {

        switch (item.getTitle()){

            case "Payments":
                Intent intentPayments = new Intent(this, PaymentsActivity.class);
                startActivity(intentPayments);
            case "Vendors":
                Intent intentVendor = new Intent(this, VendorActivity.class);
                startActivity(intentVendor);
            case "Notice Board":
                Intent intentNotice = new Intent(this, NoticeAcivity.class);
                startActivity(intentNotice);
            case "Society Rules":
                Intent intentRules = new Intent(this, SocietyRules.class);
                startActivity(intentRules);
            case "Complaints":
                Intent intentComplaints = new Intent(this, ComplaintActivity.class);
                startActivity(intentComplaints);
        }
        Toast.makeText(this, "is clicked" + item.getTitle(), Toast.LENGTH_SHORT).show();
    }

    public void permissionRequest(){

                if (checkPermission()) {

                    Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();

                } else {

                    Snackbar.make(view, "Please request permission.", Snackbar.LENGTH_LONG).show();
                    requestPermission();
                }

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE );
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CALL_PHONE, INTERNET}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted)
                        Snackbar.make(view, "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();
                    else {

                        Snackbar.make(view, "Permission Denied, You cannot access location data and camera.", Snackbar.LENGTH_LONG).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CALL_PHONE)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CALL_PHONE, INTERNET},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }
                break;
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


}
