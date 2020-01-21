package com.example.housingsocietyapp.View.UI.UI;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.ArrayRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housingsocietyapp.Model.Database.NoticeTable;
import com.example.housingsocietyapp.R;
import com.example.housingsocietyapp.View.UI.UI.Adapter.NoticeAdapter;
import com.example.housingsocietyapp.ViewModel.NoticeViewModel;

import java.util.ArrayList;
import java.util.List;

public class NoticeAcivity extends AppCompatActivity {
    private static final String TAG = "NoticeAcivity";

    private NoticeViewModel noticeViewModel;

    RecyclerView recyclerView;
    ArrayList<NoticeTable> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        setUpRecyclerView();
    }

    public void setUpRecyclerView(){

        recyclerView = findViewById(R.id.notice_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoticeAdapter adapter = new NoticeAdapter();
        recyclerView.setAdapter(adapter);

        arrayList = new ArrayList<>();

        noticeViewModel  = ViewModelProviders.of(this).get(NoticeViewModel.class);
        noticeViewModel.getListLiveData().observe(this, new Observer<List<NoticeTable>>() {
            @Override
            public void onChanged(List<NoticeTable> noticeTables) {

                adapter.setNotice(noticeTables);
                Toast.makeText(NoticeAcivity.this, "OnChange", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
