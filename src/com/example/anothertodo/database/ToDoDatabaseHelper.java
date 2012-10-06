package com.example.anothertodo.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.anothertodo.model.Task;


public class ToDoDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "anothertodo.db";
	private static final int DATABASE_VERSION = 2;
	public static final String TABLE_TASKS = "tasks";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	
	private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_TASKS + " (" + COLUMN_ID
               + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  COLUMN_NAME
               + " TEXT NOT NULL );";
	
	 

	public ToDoDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		System.out.println("Database Created");

	}

	@Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(ToDoDatabaseHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
	    onCreate(db);
	  }
	

    public void insertTask(Task task){
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("insert into "+TABLE_TASKS+
                        " (" + COLUMN_NAME +  ") values ( ? )");
        stmt.bindString(1, task.getName());
        stmt.executeInsert();
    }
    
    public void deleteTask(Task task){
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("delete from "+TABLE_TASKS+
                        " where " + COLUMN_ID +  "= ? ");
        stmt.bindLong(1, task.getId());
        stmt.executeInsert();
    }
    
    public List<Task> getAllTasks(){
        Cursor cursor = getReadableDatabase().query(TABLE_TASKS, new String[]{COLUMN_ID, COLUMN_NAME}, null, null, null, null, null);
        List<Task> taskList = new ArrayList<Task>();
        while (cursor.moveToNext()){
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            taskList.add(new Task(id, name));
        }
        return taskList;
    }

}
