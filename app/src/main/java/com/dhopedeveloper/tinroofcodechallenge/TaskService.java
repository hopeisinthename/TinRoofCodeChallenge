package com.dhopedeveloper.tinroofcodechallenge;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskService {

    @GET("todos")
    Call<Tasks> getTasks();
}
