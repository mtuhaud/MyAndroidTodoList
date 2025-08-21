package com.example.myandroidtodolist.api;

import com.example.myandroidtodolist.model.TodoApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoApi {

    @GET("/todos")
    Call<TodoApiResponse> getTodos();
}
