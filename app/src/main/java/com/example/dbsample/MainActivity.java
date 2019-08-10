package com.example.dbsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

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
                    Intent intent = new Intent(getApplicationContext(), WorkoutList.class);
                    startActivity(intent);
                }

                else if (position == 1)
                {
                    Intent intent = new Intent(getApplicationContext(), AddWorkouts.class);
                    startActivity(intent);
                }

            }
        });
    }
}
