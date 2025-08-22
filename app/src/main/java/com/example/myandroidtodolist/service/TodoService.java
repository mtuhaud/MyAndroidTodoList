package com.example.myandroidtodolist.service;


import com.example.myandroidtodolist.api.TodoApi;
import com.example.myandroidtodolist.model.TodoApiResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoService {

    private final TodoApi api;

    public TodoService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")  // ton serveur local
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(TodoApi.class);
    }

    public Call<TodoApiResponse> getTodos() {
        return api.getTodos();
    }
}

