package com.example.myandroidtodolist.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myandroidtodolist.R;
import com.example.myandroidtodolist.model.Todo;
import java.util.List;
import android.content.Intent;
import android.util.Log;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private final List<Todo> todos;

    public TodoAdapter(List<Todo> todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.title.setText(todo.getTitle());
        holder.status.setText(todo.isCompleted() ? "✅" : "❌");

        holder.itemView.setOnClickListener(v -> {
            Log.d("TodoAdapter", "Item cliqué : " + todo.getId());
            Intent intent = new Intent(v.getContext(), TodoDetailActivity.class);
            intent.putExtra(TodoDetailActivity.EXTRA_TODO_ID, todo.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView title, status;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.todoTitle);
            status = itemView.findViewById(R.id.todoStatus);
        }
    }
}
