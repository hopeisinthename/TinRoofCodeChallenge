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
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {Task.class}, version = 1 , exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    private static List<Task> taskList;

    public abstract TaskDao taskDao();

    public static TaskDatabase taskDatabase;

    private static TaskService service;


    public synchronized static TaskDatabase getTaskDatabaseInstance() {
        if (taskDatabase == null) {
            taskList = new ArrayList<>();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(NetworkUtil.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            retrofit.create(TaskService.class);

            service.getTasks().enqueue(new Callback<Tasks>() {
                @Override
                public void onResponse(Call<Tasks> call, Response<Tasks> response) {

                    if (response.isSuccessful()) {

                        if (response.body().getTaskList() != null)
                        taskList.addAll(response.body().getTaskList());

                    }
                }

                @Override
                public void onFailure(Call<Tasks> call, Throwable t) {

                }
            });

        }

        return taskDatabase;
    }




    private static Builder<TaskDatabase> buildDatabase(final Context context) {

        return Room.databaseBuilder(context,
                                    TaskDatabase.class,
                                    "my_database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        Executors.newSingleThreadScheduledExecutor().execute(
                                () -> getTaskDatabaseInstance(context).taskDao().insertTasks(taskList));
                    }
                });

    }

}
