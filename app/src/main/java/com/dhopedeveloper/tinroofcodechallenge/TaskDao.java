package com.dhopedeveloper.tinroofcodechallenge;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task_table " + " ORDER BY completed DESC LIMIT 1")
    LiveData<List<Task>> getAllIncompleteTasks();

    @Insert()
    void insertTasks(List<Task> taskList);


}
