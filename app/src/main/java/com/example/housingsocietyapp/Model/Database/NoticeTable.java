package com.example.housingsocietyapp.Model.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notice_table")
public class NoticeTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int count;

    private String noticeText;

    private String timeStamp;

    public NoticeTable( String noticeText, String timeStamp) {
        this.noticeText = noticeText;
        this.timeStamp = timeStamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNoticeText() {
        return noticeText;
    }

    public void setNoticeText(String noticeText) {
        this.noticeText = noticeText;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "NoticeTable{" +
                "count=" + count +
                ", noticeText='" + noticeText + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
