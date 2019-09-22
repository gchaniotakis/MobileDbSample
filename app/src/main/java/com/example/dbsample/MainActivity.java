package com.example.dbsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     Cursor cursor;
    SQLiteDatabase db;
    String [] findCols = {DBHelper.KEY_ROWID, DBHelper.KEY_TITLE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
                    if(checkNetworkConnection(getApplicationContext()) == true)
                    {
                        Intent intent = new Intent(getApplicationContext(), NetworkList.class);
                        startActivity(intent);
                    }

                    else
                    {
                        Toast.makeText(MainActivity.this,"Connect to wifi or mobile data", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private boolean checkNetworkConnection(Context context)
    {
        int [] networkTypes = {ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE};

        try
        {
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

            for(int networkType: networkTypes)
            {
                if(activeNetwork != null && activeNetwork.getType() == networkType)
                {
                    return true;
                }
            }
        }

        catch (Exception e)
        {
            return false;
        }

        return false;
    }



    @Override
    protected void onResume()
    {
        DBHelper helper = new DBHelper(this);
        db = helper.getReadableDatabase();

        super.onResume();
        cursor = db.query(DBHelper.TABLE_NAME,findCols,null, null, null, null, null);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if(cursor != null)
        {
            cursor.close();
        }

        if(db != null)
        {
            db.close();
        }


    }
}
