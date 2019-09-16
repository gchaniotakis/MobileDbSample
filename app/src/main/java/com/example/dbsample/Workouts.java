package com.example.dbsample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Workouts extends AppCompatActivity {

    public static final String EXTRA_ID ="Id";

    private static final String Tag = "Workouts";

    private SQLiteDatabase db;

    private Cursor cursorl;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workouts);

        final int workoudID = (Integer)getIntent().getExtras().get(EXTRA_ID);

        try
        {
            DBHelper helper  = new DBHelper(this);

            db = helper.getReadableDatabase();

            String [] tableCols = {DBHelper.KEY_ROWID, DBHelper.KEY_TITLE, DBHelper.KEY_SETS, DBHelper.KEY_REPS};

            String  whereClause = DBHelper.KEY_ROWID + " =?";
            String [] whereArgs = {Integer.toString(workoudID)};

            cursorl = db.query(DBHelper.TABLE_NAME, tableCols, whereClause, whereArgs, null, null,null);

            cursorl.moveToFirst();

            String t = cursorl.getString(1);
            String s = cursorl.getString(2);
            String r = cursorl.getString(3);
            String d = cursorl.getString(4);

            TextView txt1 = (TextView)findViewById(R.id.inputTitle);
            TextView txt2 = (TextView)findViewById(R.id.inputSets);
            TextView txt3 = (TextView)findViewById(R.id.inputReps);
            TextView txt4 = (TextView)findViewById(R.id.inputDesc);

            txt1.setText(t);
            txt2.setText(s);
            txt3.setText(r);
            txt4.setText(d);

            cursorl.close();
            db.close();
        }

        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
    }
}
