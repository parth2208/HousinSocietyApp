package com.example.housingsocietyapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.housingsocietyapp.MainActivity;
import com.example.housingsocietyapp.R;
import com.example.housingsocietyapp.View.UI.UI.HistoryActivity;
import com.example.housingsocietyapp.View.UI.UI.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationHelper {

    private static final String TAG = "BottomNavigationVewHelp";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting bottom helper");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx viewEx){

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home :

                        Intent intentHome = new Intent(context, MainActivity.class);
                        context.startActivity(intentHome);
                        break;

                    case R.id.ic_profile :

                        Intent intetVehicle = new Intent(context, ProfileActivity.class);
                        context.startActivity(intetVehicle);
                        break;

                    case R.id.ic_history :
                        Intent intentTransaction = new Intent(context, HistoryActivity.class);
                        context.startActivity(intentTransaction);
                        break;

                }
                return false;
            }
        });
    }

}