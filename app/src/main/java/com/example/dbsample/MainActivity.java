package com.example.dbsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     Cursor cursor;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = new DBHelper(this);

        db = helper.getReadableDatabase();
        String [] findCols = {DBHelper.KEY_ROWID, DBHelper.KEY_TITLE};


         cursor = db.query(DBHelper.TABLE_NAME,findCols,null, null, null, null, null);

        ListView list = (ListView)findViewById(R.id.mainList);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(position == 0)
                {
                    if(cursor.moveToFirst())
                    {
                        Intent intent = new Intent(getApplicationContext(), WorkoutList.class);
                        startActivity(intent);
                    }

                    else
                    {
                        Toast.makeText(MainActivity.this,"Add some workouts first!",Toast.LENGTH_SHORT).show();
                    }
                }

                else if (position == 1)
                {
                    Intent intent = new Intent(getApplicationContext(), AddWorkouts.class);
                    startActivity(intent);
                }

                else if (position == 2)
                {

                }

            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
