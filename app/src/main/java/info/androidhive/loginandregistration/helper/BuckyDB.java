package info.androidhive.loginandregistration.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import info.androidhive.loginandregistration.activity.sessionModel;

/**
 * Created by dioscar on 8/21/2016.
 */
public class BuckyDB extends SQLiteOpenHelper {
//Booking
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SessionsBooked.db";
    public static final String TABLE_SESSIONSBOOKED = "booked";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BOOKED_DATE = "bookedDate";

    //User local information
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_NICKNAME = "nickname";
    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_CREATED = "created";
    public static final String COLUMN_USER_MODIFIED = "modified";
    public static final String COLUMN_USER_ISDELETED = "isdelted";


    public BuckyDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Call on first time created
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_SESSIONSBOOKED +"(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_BOOKED_DATE + " TEXT "+
                        ");";
        db.execSQL(query);
    }
    // call when version change.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + "TABLE_SESSIONSBOOKED");
        onCreate(db);
    }

    //Add n new row to the database
    public void addsessionModel(sessionModel model){

        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOKED_DATE, model.get_BookedDate());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SESSIONSBOOKED,null,values);
        db.close();
    }

    //Delete a model from a database
    public void deleteSessionModel(String BookedDate)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELTE FROM "+ COLUMN_BOOKED_DATE +" WHERE " +COLUMN_BOOKED_DATE + " =\" "+ BookedDate +"\";" );
    }

    // PRINT RECORDS
    public ArrayList<String> getAllSessions()
    {
        String SessionsdbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SESSIONSBOOKED + " WHERE 1;";
       // db.execSQL(query);

        Cursor c = db.rawQuery(query,null);

        ArrayList<String> mArrayList = new ArrayList<String>();
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            // The Cursor is now set to the right position
            mArrayList.add(c.getString(c.getColumnIndex("bookedDate")));
        }

        db.close();
        return mArrayList;
    }



}
