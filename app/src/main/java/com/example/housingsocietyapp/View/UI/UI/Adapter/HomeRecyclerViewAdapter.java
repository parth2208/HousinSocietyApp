package com.example.housingsocietyapp.View.UI.UI.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housingsocietyapp.Model.LocalModel.HomeRecyclerDataModel;
import com.example.housingsocietyapp.R;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "HomeRecyclerViewAdapter";

    private ArrayList<HomeRecyclerDataModel> values;
    private Context context;
    private ItemListener itemListener;

    public HomeRecyclerViewAdapter(ArrayList<HomeRecyclerDataModel> values, Context context, ItemListener itemListener) {
        this.values = values;
        this.context = context;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final HomeRecyclerDataModel model = values.get(position);

        holder.textView.setText(model.getTitle());
        holder.imageView.setImageResource(model.banner);
        holder.relativeLayout.setBackgroundColor(Color.parseColor(model.color));

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        HomeRecyclerDataModel item;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            textView =  itemView.findViewById(R.id.home_textView);
            imageView = itemView.findViewById(R.id.home_banner);
            relativeLayout = itemView.findViewById(R.id.homeConstraint);
        }

        @Override
        public void onClick(View v) {

            if (itemListener!= null){
                itemListener.onItemClick(item);
            }
        }
    }

    public interface ItemListener{
        void onItemClick(HomeRecyclerDataModel item);
    }
}
