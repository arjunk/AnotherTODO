package com.example.anothertodo.database;

import java.util.List;

import android.content.Context;

import com.example.anothertodo.model.Task;

public class TodoRepository {
	
	private ToDoDatabaseHelper dbHelper;
	
	public TodoRepository(Context context){
		dbHelper = new ToDoDatabaseHelper(context);
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public void addTask(Task task){
		dbHelper.insertTask(task);
	}
	
	public void removeTask(Task task){
		dbHelper.deleteTask(task);
	}
	
	public List<Task> getAllTasks(){
		return dbHelper.getAllTasks();
	}

}
