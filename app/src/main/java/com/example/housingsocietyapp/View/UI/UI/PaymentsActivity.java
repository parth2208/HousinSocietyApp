package com.example.housingsocietyapp.View.UI.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.housingsocietyapp.Model.Database.Payments;
import com.example.housingsocietyapp.R;
import com.example.housingsocietyapp.View.UI.UI.Adapter.PaymentDetailsAdapter;
import com.example.housingsocietyapp.ViewModel.PaymentsViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PaymentsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "PaymentsActivity";

    private PaymentsViewModel paymentsViewModel;

    AppCompatSpinner billTypeSpinner;
    AppCompatImageButton payment_submit_btn;
    TextInputEditText paymentAmount, paymentCycle,remarks;

    private Payments payments;
    private String billType;
    private RecyclerView recyclerView;
    private ArrayList<Payments> paymentsArrayList;

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        paymentsViewModel = ViewModelProviders.of(this).get(PaymentsViewModel.class);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,R.array.bill_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        billTypeSpinner.setAdapter(adapter);
        billTypeSpinner.setOnItemSelectedListener(this);

        initWidget();
        savePaymentDetails();
        setUpRecyclerView();
    }

    public void initWidget(){
        billTypeSpinner = findViewById(R.id.payments_type_spinner);
        payment_submit_btn = findViewById(R.id.submit_payment_btn);
        paymentAmount = findViewById(R.id.editText_payment_amount);
        paymentCycle = findViewById(R.id.editText_payments_cycle);
        remarks = findViewById(R.id.editText_payments_remarks);
    }

    //Required For Fill Entire Forms
    private boolean checkinputs(String cycle, String remarks, String amount) {
        Log.d(TAG, "checkinputs: Checking if input field is filled or not");

        if (cycle.equals("") || remarks.equals("") || amount.equals("")){
            Toast.makeText(context, "Please fill all the field.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void setUpRecyclerView(){

        recyclerView = findViewById(R.id.payments_history_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PaymentDetailsAdapter adapter = new PaymentDetailsAdapter();
        recyclerView.setAdapter(adapter);

        paymentsArrayList = new ArrayList<>();

        paymentsViewModel  = ViewModelProviders.of(this).get(PaymentsViewModel.class);
        paymentsViewModel.getListLiveData().observe(this, new Observer<List<Payments>>() {
            @Override
            public void onChanged(List<Payments> payments) {
                adapter.setPaymentsDetails(paymentsArrayList);
                Toast.makeText(PaymentsActivity.this, "OnChange", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void savePaymentDetails(){
        Log.d(TAG, "savePaymentDetails: Initializing Payments");

        payment_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = Objects.requireNonNull(paymentAmount.getText()).toString();
                int billAmount = Integer.valueOf(amount);
                String type = billType;
                String cycle = Objects.requireNonNull(paymentCycle.getText()).toString();
                String paymentRemarks = Objects.requireNonNull(remarks.getText()).toString();

                String payment_time = DateFormat.getDateTimeInstance().format(new Date());

                if (checkinputs(cycle,paymentRemarks,amount)){
                    int getUUID = UUID.randomUUID().hashCode();

                    payments = new Payments(type,cycle,billAmount,paymentRemarks,getUUID);
                    paymentsViewModel.insert(payments);

                    Intent intent = new Intent(context, SubmitPaymentActivity.class);
                    intent.putExtra("unique_id",getUUID);
                    startActivity(intent);

                    paymentCycle.setText("");
                    remarks.setText("");
                    paymentAmount.setText("");
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Log.d(TAG, "onItemSelected: Selecting Vehicle Type");

        String bill_type = parent.getItemAtPosition(position).toString();

        //storing data into table
        if (position ==0){

            Log.d(TAG, "onItemSelected: Its the default vehicle type");
        }else {

            billType = bill_type;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
