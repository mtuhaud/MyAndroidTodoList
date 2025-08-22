package com.example.myandroidtodolist.api;

import com.example.myandroidtodolist.model.TodoApiResponse;
import com.example.myandroidtodolist.model.Todo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TodoApi {

    @GET("/todos")
    Call<TodoApiResponse> getTodos();

    @GET("/todos/{id}")
    Call<Todo> getTodoById(@Path("id") int id);

}
