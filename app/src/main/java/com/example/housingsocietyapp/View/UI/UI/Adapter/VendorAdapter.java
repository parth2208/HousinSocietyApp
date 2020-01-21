package com.example.housingsocietyapp.View.UI.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housingsocietyapp.Model.Database.VendorsTable;
import com.example.housingsocietyapp.R;

import java.util.ArrayList;
import java.util.List;

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.VendorViewHolder> {


    private List<VendorsTable> vendorList;
    private Context context;
    private ItemListener itemListener;

    public VendorAdapter(Context context, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public VendorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_recycler_item,parent,false);
        return new VendorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorViewHolder holder, int position) {

        final VendorsTable model = vendorList.get(position);

        holder.vendorName.setText(model.getName());
        holder.vendorAddress.setText(model.getAddress());
        holder.vendortype.setText(model.getType());
        holder.vendorPhone.setText(model.getPhone());
    }

    @Override
    public int getItemCount() {
        return vendorList.size();
    }

    public void setNotice(List<VendorsTable> notice){
        this.vendorList = notice;
        notifyDataSetChanged();
    }

    public class VendorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView vendorName;
        private TextView vendorAddress;
        private TextView vendorPhone;
        private TextView vendortype;
        private ConstraintLayout constraintLayout;
        VendorsTable item;



        public VendorViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            vendorName =  itemView.findViewById(R.id.vendor_name);
            vendorAddress =  itemView.findViewById(R.id.vendor_address);
            vendorPhone =  itemView.findViewById(R.id.vendor_phone);
            vendortype =  itemView.findViewById(R.id.vendor_type);
            constraintLayout = itemView.findViewById(R.id.vendorConstraint);
        }

        @Override
        public void onClick(View v) {
            if (itemListener!= null){
                itemListener.onItemClick(item);
            }
        }
    }


    public interface ItemListener{
        void onItemClick(VendorsTable item);
    }
}
