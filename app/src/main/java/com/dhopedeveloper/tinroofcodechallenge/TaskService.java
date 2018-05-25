package com.dhopedeveloper.tinroofcodechallenge;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskService {

    @GET("todos")
    Call<List<Task>> getTasks();
}
