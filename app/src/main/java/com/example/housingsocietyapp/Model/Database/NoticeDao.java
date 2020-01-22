package com.example.housingsocietyapp.Model.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoticeDao {

    @Insert
    void newNotice(NoticeTable noticeTable);

    @Query("SELECT noticeText, timeStamp FROM notice_table")
    LiveData<List<NoticeTable>> getAllNotice();

}
