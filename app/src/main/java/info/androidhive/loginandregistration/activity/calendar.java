package info.androidhive.loginandregistration.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import info.androidhive.loginandregistration.R;
import info.androidhive.loginandregistration.helper.BuckyDB;
import info.androidhive.loginandregistration.helper.SQLiteHandler;
import info.androidhive.loginandregistration.helper.SessionManager;

public class calendar extends AppCompatActivity
{
    private BuckyDB db;
    private Toolbar toolbar; //Menu

    private SQLiteHandler dbApi; //database
    private SessionManager session;//database

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        // SqLite database handler
        dbApi = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
        // SQLite database handler
        db = new BuckyDB(this,null,null,1);
        toolbar = (Toolbar) findViewById(R.id.app_bar); //Toolbar
        setSupportActionBar(toolbar); //Toolbar
        getSupportActionBar().setTitle("Book a session"); //Toolbar
        getSupportActionBar().setSubtitle("SubTitle"); //Toolbar
        getSupportActionBar().setIcon(R.drawable.ic_action_name);//Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarviewBookaSession);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                int d = dayOfMonth;
                int dmonth  = month;
                int dyear = year;
                String DateBookedString = dmonth+1 + "-" + d + "-" + dyear;
                AddBoookeDate(DateBookedString);
                Toast.makeText(view.getContext(),  dmonth+1 + "-" + d + "-" + dyear, Toast.LENGTH_LONG).show();
            }
    });
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

        switch (item.getItemId())
        {
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
    private void AddBoookeDate(final String DateBooked )
    {

        String DateBookedstr = DateBooked;
        // Inserting row in Session_Booked table
        String created_at = "08-21-2016";
        sessionModel model = new sessionModel(0,DateBooked,created_at);
        db.addsessionModel(model);
        Toast.makeText(getApplicationContext(), "Booked!", Toast.LENGTH_LONG).show();

        // Launch login activity
        Intent intent = new Intent(
                calendar.this,
                mysessions.class);
        startActivity(intent);
       // finish();
    };
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
