package com.example.dbsample;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class WorkoutList extends ListActivity {

    private static final  String TAG = "WorkoutList";
    private SimpleCursorAdapter cursorAdapter;
    private ListView listView;
    private Cursor cursor;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        listView = getListView();

        int layout = android.R.layout.simple_expandable_list_item_1;

        String [] findCols = {DBHelper.KEY_ROWID, DBHelper.KEY_TITLE};

        String [] displaysCols ={DBHelper.KEY_TITLE};

        int [] to = {android.R.id.text1};

        cursor = null;

        cursorAdapter = new SimpleCursorAdapter(this, layout,cursor,displaysCols,to,0);

        listView.setAdapter(cursorAdapter);

    }

    @Override

    protected void onResume()
    {
        super.onResume();

        cursor = updateCursor();

        cursorAdapter.changeCursor(cursor);
    }

    private  Cursor updateCursor()
    {
        cursor = null;

        try
        {
            DBHelper dbHelper  = new DBHelper(this);

            db = dbHelper.getReadableDatabase();
            String [] findCols = {DBHelper.KEY_ROWID, DBHelper.KEY_TITLE};

            cursor = db.query(DBHelper.TABLE_NAME, findCols, null, null, null ,null, null);
        }

        catch (SQLiteException e)
        {
            e.printStackTrace();
        }

        return cursor;
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id)
    {
        Intent intent = new Intent(this, Workouts.class);

        intent.putExtra(Workouts.EXTRA_ID, (int) id);

        startActivity(intent);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        if(cursor != null)
        {
            cursor.close();
        }

        if (db != null)
        {
            db.close();
        }


    }
}
