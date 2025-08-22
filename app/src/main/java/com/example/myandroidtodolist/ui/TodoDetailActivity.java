package com.example.myandroidtodolist.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myandroidtodolist.model.Todo;
import com.example.myandroidtodolist.service.TodoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.myandroidtodolist.R;

public class TodoDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_ID = "todo_id";

    private TextView textViewDetail;
    private TodoService todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        textViewDetail = findViewById(R.id.textViewDetail);
        todoService = new TodoService();

        int todoId = getIntent().getIntExtra(EXTRA_TODO_ID, -1);
        if (todoId != -1) {
            fetchTodoDetail(todoId);
        } else {
            textViewDetail.setText("ID invalide");
        }
    }

    private void fetchTodoDetail(int todoId) {
        todoService.getTodoById(todoId).enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Todo todo = response.body();
                    textViewDetail.setText(
                            "Titre: " + todo.getTitle() + "\n" +
                                    "Description: " + (todo.getDescription() != null ? todo.getDescription() : "-") + "\n" +
                                    "Statut: " + (todo.isCompleted() ? "✅ Terminé" : "❌ En cours")
                    );
                } else {
                    textViewDetail.setText("Erreur API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                textViewDetail.setText("Échec: " + t.getMessage());
            }
        });
    }
}

