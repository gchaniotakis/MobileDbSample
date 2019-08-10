package com.example.dbsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {


   static final String TABLE_NAME = "WorkoutTable";
   static final String KEY_ROWID = "_id";
   static final String KEY_TITLE = "Title";
   static final String KEY_REPS = "Reps";
   static final String KEY_SETS = "Sets";
   static final String KEY_DESC = "Description";

   private static final int DB_VERSION = 1;
   private static final String DB_NAME = "WorkoutDatabase";

   private static SQLiteDatabase DB;

   private String TAG = "DBHelper";

   private static  String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +" (" + KEY_ROWID +
           " integer PRIMARY KEY, " + KEY_TITLE + ", " + KEY_SETS + ", " + KEY_REPS + ", "
           + KEY_DESC + " ) ;";

   DBHelper(Context context)
   {
       super(context, DB_NAME, null, DB_VERSION);
   }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL(CREATE_TABLE);
       Log.d(TAG, "Table created succesfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
       onCreate(sqLiteDatabase);
    }

    public void insertValues(String title, String sets, String reps, String desc)
    {
        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, title);
        values.put(KEY_SETS, sets);
        values.put(KEY_REPS, reps);
        values.put(KEY_DESC, desc);

        DB = this.getWritableDatabase();
        DB.insert(TABLE_NAME, null, values);
        Log.d(TAG, "Values inserted");
    }
}
