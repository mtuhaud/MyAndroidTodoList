package com.example.myandroidtodolist.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myandroidtodolist.R;
import com.example.myandroidtodolist.model.Todo;
import com.example.myandroidtodolist.model.TodoApiResponse;
import com.example.myandroidtodolist.service.TodoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private List<Todo> todosList = new ArrayList<>();
    private TodoService todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewTodos);
        adapter = new TodoAdapter(todosList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoService = new TodoService();
        fetchTodos();
    }

    private void fetchTodos() {
        todoService.getTodos().enqueue(new Callback<TodoApiResponse>() {
            @Override
            public void onResponse(Call<TodoApiResponse> call, Response<TodoApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    todosList.clear();
                    todosList.addAll(response.body().getTodos());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("MainActivity", "Erreur API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TodoApiResponse> call, Throwable t) {
                Log.e("MainActivity", "Échec: " + t.getMessage());
            }
        });
    }
}
