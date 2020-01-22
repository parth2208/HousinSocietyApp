package com.example.housingsocietyapp.View.UI.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.housingsocietyapp.R;
import com.example.housingsocietyapp.Utils.ExpireSlashFormat;
import com.example.housingsocietyapp.Utils.FourDigitFormat;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.text.DateFormat;
import java.util.Date;

public class SubmitPaymentActivity extends AppCompatActivity {
    private static final String TAG = "SubmitPaymentActivity";

    AppCompatImageButton payment_button;

    AppCompatEditText card_no,cvv,exp_date,holder_name;
    TextView uuid;
    EasyFlipView easyFlipView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_payment);

        initWidget();

        int unique_id = getIntent().getIntExtra("unique_id",0);
        uuid.setText(Integer.toString(unique_id));
        card_no.addTextChangedListener(new FourDigitFormat());
        exp_date.addTextChangedListener(new ExpireSlashFormat());
    }

    public void initWidget(){
        payment_button = findViewById(R.id.submit_payment_btn);
        card_no = findViewById(R.id.edit_card_no);
        holder_name = findViewById(R.id.edit_card_holder);
        cvv = findViewById(R.id.edit_card_cvv);
        exp_date = findViewById(R.id.edit_exp_date);
        easyFlipView = findViewById(R.id.easy_card_flip);
        uuid = findViewById(R.id.db_unique_id);
    }

    public void payment_next_btn(){
        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Its working correctly ");

                String payment_time = DateFormat.getDateTimeInstance().format(new Date());
                String card_number = card_no.getText().toString();
                String customer_name = holder_name.getText().toString();
                String card_cvv = cvv.getText().toString();
                String expiry = exp_date.getText().toString();

                if (checkinputs(card_number,customer_name,card_cvv,expiry)){
                    card_no.setText("");
                    holder_name.setText("");
                    cvv.setText("");
                    exp_date.setText("");

                    // TODO: 1/22/2020 Integration of Payment System
                    Toast.makeText(SubmitPaymentActivity.this, "Payment Done Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SubmitPaymentActivity.this,PaymentsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean checkinputs(String number, String name, String cvv, String expiry) {
        Log.d(TAG, "checkinputs: Checking if input field is filled or not");
        if (number.equals("") || name.equals("") || cvv.equals("") || expiry.equals("")) {
            Toast.makeText(this, "Please fill all the field.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
