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

import info.androidhive.loginandregistration.Model.ScheduleModel;
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
    }

//    public void askForCalendarPermission() {
//
//    }



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

                break;
            case R.id.menu_2:
                logoutUser();
                break;

        }
        return super.onContextItemSelected(item);
    }

    private void AddBoookeDate(final String DateBooked, int year, int month, int day, int hour, int minutes)
    {

        // Launch login activity
        String email = "Dioscarr@gmail.com";
        String title = "Leven Record Session";
        String description = "Recording Vocals";
        String time_zone = "Leven's Studio";
        ScheduleModel smObj = new ScheduleModel(0,year,month,day,hour,minutes,email, title,description,time_zone,"");
        Intent intent = new Intent(
                calendar.this,
                schedule.class);
        intent.putExtra("scheduleModel",smObj);
        startActivity(intent);

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
