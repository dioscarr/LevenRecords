package info.androidhive.loginandregistration.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

import info.androidhive.loginandregistration.R;
import info.androidhive.loginandregistration.helper.BuckyDB;
import info.androidhive.loginandregistration.helper.SQLiteHandler;
import info.androidhive.loginandregistration.helper.SessionManager;

public class calendar extends AppCompatActivity {
    private static final String TAG = null;
    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 0;
    private BuckyDB db;
    private Toolbar toolbar; //Menu
    private SQLiteHandler dbApi; //database
    private SessionManager session;//database
    Cursor cur = null;

    public static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };

    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        // SqLite database handler
        dbApi = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
        // SQLite database handler
        db = new BuckyDB(this, null, null, 1);
        toolbar = (Toolbar) findViewById(R.id.app_bar); //Toolbar
        setSupportActionBar(toolbar); //Toolbar
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.toolbaricon, null);
        getSupportActionBar().setCustomView(mCustomView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Book a session"); //Toolbar
        getSupportActionBar().setSubtitle("SubTitle"); //Toolbar
        //getSupportActionBar().setIcon(R.drawable.ic_action_name);//Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarviewBookaSession);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int d = dayOfMonth;
                int dmonth = month;
                int dyear = year;
                String DateBookedString = dmonth + 1 + "-" + d + "-" + dyear;
                AddBoookeDate(DateBookedString, dyear, dmonth, d, 18, 30);
                Toast.makeText(view.getContext(), dmonth + 1 + "-" + d + "-" + dyear, Toast.LENGTH_LONG).show();
            }
        });
        //Calendar
        // Run query
        //Prepare credentials for calendar access

        // Submit the query and get a Cursor object back.
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CALENDAR)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, MY_PERMISSIONS_REQUEST_READ_CALENDAR);

                // MY_PERMISSIONS_REQUEST_READ_CALENDAR is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"dioscarr@gmail.com", "com.google", "dioscarr@gmail.com"}; // dummy data: TODO: get current user email


        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);


    }

    public void askForCalendarPermission() {

    }

    //This method will be call when the app has no permission to calendar
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALENDAR: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "permission was granted, yay!", Toast.LENGTH_LONG).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /***
     * This method is inherited from the appCompatActivity class
     * and it initialize the menu on the toolbar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * this is a method from the inherited class that get
     * the selected item id from the tool bar menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_1:
                // Launching the login settings
                Intent intent = new Intent(calendar.this, settings.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_2:
                logoutUser();
                break;

        }
        return super.onContextItemSelected(item);
    }

    private void AddBoookeDate(final String DateBooked, int year, int month, int day, int hour, int minutes) {

        String DateBookedstr = DateBooked;
        int localYear = year;
        int localMonth = month;
        int localDay = day;
        int localHour = hour;
        int localMinutes = minutes;
        // Inserting row in Session_Booked table
        String created_at = "08-21-2016";
        sessionModel model = new sessionModel(0, DateBooked, created_at);
        db.addsessionModel(model);
        Toast.makeText(getApplicationContext(), "Booked!", Toast.LENGTH_LONG).show();


        // Use the cursor to step through the returned records
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
            Toast.makeText(this, ownerName, Toast.LENGTH_SHORT).show();


            //Insert Calendar event by switching to the calendar
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(localYear, localMonth, localDay, localHour, localMinutes); //remember calendar is zero base
            Calendar endTime = Calendar.getInstance();
            endTime.set(localYear, localMonth, localDay, localHour + 2, localMinutes);
//            Intent Cintent = new Intent(Intent.ACTION_INSERT)
//                    .setData(CalendarContract.Events.CONTENT_URI)
//                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
//                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
//                    .putExtra(CalendarContract.Events.TITLE, "Leven Record Session")
//                    .putExtra(CalendarContract.Events.DESCRIPTION, "Recording Vocals")
//                    .putExtra(CalendarContract.Events.EVENT_LOCATION, "Leven's Studio")
//                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
//                    .putExtra(Intent.EXTRA_EMAIL, String.valueOf(displayName));
//            startActivity(Cintent);


            ContentResolver cr = getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
            values.put(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
            values.put(CalendarContract.Events.TITLE, "Leven Record Session");
            values.put(CalendarContract.Events.DESCRIPTION, "Recording Vocals");
            values.put(CalendarContract.Events.CALENDAR_ID, calID);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/New York");
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CALENDAR)) {
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, MY_PERMISSIONS_REQUEST_READ_CALENDAR);

                    // MY_PERMISSIONS_REQUEST_READ_CALENDAR is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

// get the event ID that is the last element in the Uri
            long eventID = Long.parseLong(uri.getLastPathSegment());
//
// ... do something with event ID
//
//
        }
        // Launch login activity
        Intent intent = new Intent(
                calendar.this,
                mysessions.class);
        startActivity(intent);
        finish();
    }








    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser()
    {
        session.setLogin(false);

        dbApi.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(calendar.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
