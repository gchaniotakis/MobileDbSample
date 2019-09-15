package com.example.dbsample;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

        try {
            DBHelper helper = new DBHelper(this);

            db = helper.getReadableDatabase();

            int layout = android.R.layout.simple_expandable_list_item_1;

            String [] findCols = {DBHelper.KEY_ROWID, DBHelper.KEY_TITLE};
            String [] displaysCols ={DBHelper.KEY_TITLE};

            int [] to = {android.R.id.text1};

            cursor = db.query(DBHelper.TABLE_NAME,findCols,null,null,null,null,null);

            cursorAdapter = new SimpleCursorAdapter(this, layout,cursor,displaysCols,to,0);

            listView.setAdapter(cursorAdapter);

        }

        catch (SQLException e)
        {
            Log.e(TAG, "Something's gone wrong!");
            e.printStackTrace();
        }

    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id)
    {
        Intent intent = new Intent(this, Workouts.class);

        intent.putExtra(Workouts.EXTRA_ID, (int) id);

        startActivity(intent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
