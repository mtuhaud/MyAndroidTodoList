package com.example.myandroidtodolist.model;

import java.util.List;

public class TodoApiResponse {

    private List<Todo> todos;
    private int count;

    public List<Todo> getTodos() { return todos; }
    public int getCount() { return count; }
}
