package com.example.housingsocietyapp.View.UI.UI;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housingsocietyapp.Model.Database.VendorsTable;
import com.example.housingsocietyapp.R;
import com.example.housingsocietyapp.View.UI.UI.Adapter.VendorAdapter;
import com.example.housingsocietyapp.ViewModel.VendorsViewModel;

import java.util.ArrayList;
import java.util.List;

public class VendorActivity extends AppCompatActivity implements VendorAdapter.ItemListener {
    private static final String TAG = "VendorActivity";

    RecyclerView recyclerView;
    VendorsViewModel vendorsViewModel;
    ArrayList<VendorsTable> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        setUpRecyclerView();
    }

    public void setUpRecyclerView(){

        recyclerView = findViewById(R.id.notice_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final VendorAdapter adapter = new VendorAdapter(this,this);
        recyclerView.setAdapter(adapter);

        arrayList = new ArrayList<>();

        vendorsViewModel  = ViewModelProviders.of(this).get(VendorsViewModel.class);
        vendorsViewModel.getListLiveData().observe(this, new Observer<List<VendorsTable>>() {
            @Override
            public void onChanged(List<VendorsTable> noticeTables) {

                adapter.setNotice(noticeTables);
                Toast.makeText(VendorActivity.this, "OnChange", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(VendorsTable item) {
        // TODO: 1/22/2020 Implementation of CallerIntent
    }
}
