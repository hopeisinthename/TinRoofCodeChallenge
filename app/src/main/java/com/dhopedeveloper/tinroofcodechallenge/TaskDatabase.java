package com.dhopedeveloper.tinroofcodechallenge;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1 , exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    private static List<Task> taskList;

    public abstract TaskDao taskDao();

    public static TaskDatabase INSTANCE;


    public synchronized static TaskDatabase getTaskDatabaseInstance(Context context) {
        if (INSTANCE == null) {

            taskList = new ArrayList<>(new ArrayList<>(TaskSingleton.getInstance(context).getTaskList()));
            INSTANCE = buildDatabase(context);
        }

        return INSTANCE;
    }

    private static TaskDatabase buildDatabase(final Context context) {

        return Room.databaseBuilder(context,
                                    TaskDatabase.class,
                                    "my_database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        Executors.newSingleThreadScheduledExecutor().execute(
                                () -> getTaskDatabaseInstance(context.getApplicationContext()).taskDao().insertTasks(taskList));
                    }
                }).build();

    }

}
