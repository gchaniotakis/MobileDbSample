package com.example.dbsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddWorkouts extends AppCompatActivity {

    private  String title,desc,sets,reps;
    EditText t,d,s,r;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_workouts);

        t = (EditText)findViewById(R.id.inputTitle);
        d = (EditText)findViewById(R.id.inputDesc);
        s = (EditText)findViewById(R.id.inputSets);
        r = (EditText)findViewById(R.id.inputReps);

        Button b1 = (Button)findViewById(R.id.add_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToDB();
            }
        });
    }

    private void insertToDB(){
        title = t.getText().toString();
        desc = d.getText().toString();
        sets = s.getText().toString();
        reps = r.getText().toString();

        DBHelper helper = new DBHelper(this);

        helper.insertValues(title,desc,sets,reps);

        this.finish();
    }

}
