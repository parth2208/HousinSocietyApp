package com.example.housingsocietyapp.View.UI.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housingsocietyapp.Model.Database.Payments;
import com.example.housingsocietyapp.R;

import java.util.List;

public class PaymentDetailsAdapter extends RecyclerView.Adapter<PaymentDetailsAdapter.PaymentDetailsHolder> {

    List<Payments> paymentsList;

    @NonNull
    @Override
    public PaymentDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_recycler_view,parent,false);
        return new PaymentDetailsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentDetailsHolder holder, int position) {
        final Payments payments = paymentsList.get(position);

        holder.paymentsType.setText(payments.getBillType());
        holder.paymentTimeStamp.setText(payments.getPaymentTimeStamp());
        holder.paymentAmount.setText(payments.getPaymentAmount());
        holder.paymentCycle.setText(payments.getPaymentCycle());

    }

    @Override
    public int getItemCount() {
        return paymentsList.size();
    }

    public void setPaymentsDetails(List<Payments> payments){
        this.paymentsList = payments;
        notifyDataSetChanged();
    }

    public class PaymentDetailsHolder extends RecyclerView.ViewHolder{

        public TextView paymentsType;
        public TextView paymentAmount;
        public TextView paymentCycle;
        public TextView paymentTimeStamp;

        public PaymentDetailsHolder(@NonNull View itemView) {
            super(itemView);

            paymentAmount =  itemView.findViewById(R.id.payments_amount);
            paymentCycle =  itemView.findViewById(R.id.payments_cycle);
            paymentTimeStamp =  itemView.findViewById(R.id.payments_time);
            paymentsType =  itemView.findViewById(R.id.payments_type);
        }
    }
}
