package com.example.housingsocietyapp.View.UI.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housingsocietyapp.Model.Database.NoticeTable;
import com.example.housingsocietyapp.R;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {


    private List<NoticeTable> tableArrayList;

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_recycler_view,parent,false);
        return new NoticeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {

        final NoticeTable model = tableArrayList.get(position);

        holder.noticeText.setText(model.getNoticeText());
        holder.timeText.setText(model.getTimeStamp());

    }

    @Override
    public int getItemCount() {
        return tableArrayList.size();
    }

    public void setNotice(List<NoticeTable> notice){
        this.tableArrayList = notice;
        notifyDataSetChanged();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        public TextView noticeText;
        public TextView timeText;


        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);

            noticeText =  itemView.findViewById(R.id.notice_text);
            timeText =  itemView.findViewById(R.id.notice_time);
        }

    }
}