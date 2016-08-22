package info.androidhive.loginandregistration.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import info.androidhive.loginandregistration.R;
import info.androidhive.loginandregistration.helper.BuckyDB;

public class calendar extends AppCompatActivity
{
    private BuckyDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        // SQLite database handler
        db = new BuckyDB(this,null,null,1);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarviewBookaSession);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                int d = dayOfMonth;
                int dmonth  = month;
                int dyear = year;
                String DateBookedString = dmonth + "-" + d + "-" + dyear;
                AddBoookeDate(DateBookedString);
                Toast.makeText(view.getContext(),  dmonth + "-" + d + "-" + dyear, Toast.LENGTH_LONG).show();
            }
    });
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
        finish();
    };
}
