package info.androidhive.loginandregistration.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import info.androidhive.loginandregistration.R;
import info.androidhive.loginandregistration.helper.BuckyDB;
import info.androidhive.loginandregistration.helper.SQLiteHandler;
import info.androidhive.loginandregistration.helper.SessionManager;

import static java.lang.String.valueOf;

public class mysessions extends AppCompatActivity {
BuckyDB dbHandler;
    private BuckyDB db;
    private Toolbar toolbar; //Menu

    private SQLiteHandler dbApi; //database
    private SessionManager session;//database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysessions);
        dbHandler = new BuckyDB(this,null,null,1);
        //To-Do Check if the convertion worked from ArrayList to String[] because ethe adapter needs String[]
        List<String> stockList = dbHandler.getAllSessions();
        String[] booked = new String[stockList.size()];
        booked = stockList.toArray(booked);

        ListAdapter BookedAdapter = new CustomBookedAdapter(this,booked);
        ListView BookedListView = (ListView) findViewById((R.id.listViewSessions));
        BookedListView.setAdapter(BookedAdapter);
        BookedListView.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String SingleBooked = valueOf(parent.getItemAtPosition(position));
                Toast.makeText(mysessions.this,"You have a session on: "+ SingleBooked, Toast.LENGTH_SHORT).show();

            }


        });

        // SqLite database handler
        dbApi = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
        // SQLite database handler
        db = new BuckyDB(this,null,null,1);
        toolbar = (Toolbar) findViewById(R.id.app_bar); //Toolbar
        setSupportActionBar(toolbar); //Toolbar
        getSupportActionBar().setTitle("Sessions Booked"); //Toolbar
        getSupportActionBar().setSubtitle("SubTitle"); //Toolbar
        getSupportActionBar().setIcon(R.drawable.ic_action_name);//Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View gotCalendarIdFrommysessions = (View) findViewById(R.id.goToCalendarIdFrommysessions);

        gotCalendarIdFrommysessions.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // Launching the login settings
                Intent intent = new Intent(mysessions.this, calendar.class);
                startActivity(intent);
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
                Intent intent = new Intent(mysessions.this, settings.class);
                startActivity(intent);

                break;
            case R.id.menu_2:
                logoutUser();
                break;

        }
        return super.onContextItemSelected(item);
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
        Intent intent = new Intent(mysessions.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
