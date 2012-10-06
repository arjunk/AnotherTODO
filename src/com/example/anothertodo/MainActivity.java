package com.example.anothertodo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.anothertodo.database.TodoRepository;
import com.example.anothertodo.model.Task;

public class MainActivity extends ListActivity {
	
	TodoRepository todoRepository;
	List<Task> tasks = new ArrayList<Task>(Arrays.asList(new Task("defaultTask")));
	
	private final class TaskLongClickListener implements
			OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
		    AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
		    adb.setTitle("Delete?");
		    adb.setMessage("Are you sure you want to delete " + tasks.get(position));
		    final int positionToRemove = position;
		    adb.setNegativeButton("Cancel", null);
		    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		        	todoRepository.removeTask(tasks.get(positionToRemove));
		        	refreshList();
		        }});
		    adb.show();
			return false;
		}
	}

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	private ArrayAdapter<Task> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoRepository = new TodoRepository(this);
        tasks = todoRepository.getAllTasks();
        adapter = new ArrayAdapter<Task>(this,android.R.layout.simple_list_item_1, android.R.id.text1, tasks);

        // Assign adapter to ListView
        setListAdapter(adapter); 
        getListView().setOnItemLongClickListener(new TaskLongClickListener());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
	public void addTask(View view) {
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String taskName = editText.getText().toString();
		Task task = new Task(taskName);
		todoRepository.addTask(task);
		refreshList();
		editText.setText("");
	}

	private void refreshList() {
		tasks.clear();
		tasks.addAll(todoRepository.getAllTasks());
		adapter.notifyDataSetChanged();
	}    
    
}
