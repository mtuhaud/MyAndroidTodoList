package com.example.myandroidtodolist;

import android.os.Bundle;

import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.myandroidtodolist.api.TodoApi;
import com.example.myandroidtodolist.model.Todo;
import com.example.myandroidtodolist.model.TodoApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TodoApi api = retrofit.create(TodoApi.class);

        api.getTodos().enqueue(new Callback<TodoApiResponse>() {
            @Override
            public void onResponse(Call<TodoApiResponse> call, Response<TodoApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StringBuilder sb = new StringBuilder();
                    for (Todo todo : response.body().getTodos()) {
                        sb.append(todo.getTitle())
                                .append(" - ")
                                .append(todo.isCompleted() ? "✅" : "❌")
                                .append("\n");
                    }
                    textView.setText(sb.toString());
                } else {
                    textView.setText("Erreur API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TodoApiResponse> call, Throwable t) {
                textView.setText("Échec: " + t.getMessage());
            }
        });
    }
}