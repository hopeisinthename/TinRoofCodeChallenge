package com.dhopedeveloper.tinroofcodechallenge;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskSingleton {

    public static final String TAG = TaskSingleton.class.getSimpleName();

    private static TaskSingleton taskSingleton;

    private Context context;

    private List<Task> taskList;

    public static TaskSingleton getInstance(Context context) {
        if (taskSingleton == null) {
            taskSingleton = new TaskSingleton(context);
        }

        return taskSingleton;
    }

    private TaskSingleton(Context context) {

        this.context = context.getApplicationContext();
    }



   public List<Task> getTaskList() {

       taskList = new ArrayList<>();
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(NetworkUtil.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();


       TaskService service = retrofit.create(TaskService.class);


       service.getTasks().enqueue(new retrofit2.Callback<List<Task>>() {
           @Override
           public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
               if (response.isSuccessful()) {

                   if (response.body() != null)

                       taskList.addAll(response.body());


               }
           }

           @Override
           public void onFailure(Call<List<Task>> call, Throwable t) {
               Log.v(TAG, "Tasks failed to parse: " + t);
           }
       });

       return taskList;
   }


}
