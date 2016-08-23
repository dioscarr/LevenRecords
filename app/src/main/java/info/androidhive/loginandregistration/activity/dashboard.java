package info.androidhive.loginandregistration.activity;

/**
 * Created by dionelrodriguez on 8/22/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import info.androidhive.loginandregistration.helper.SQLiteHandler;
import info.androidhive.loginandregistration.helper.SessionManager;

import info.androidhive.loginandregistration.R;


public class dashboard extends AppCompatActivity {

    private Toolbar toolbar; //Toolbar
    ActionBarDrawerToggle toggle;
    private SQLiteHandler db; //database
    private SessionManager session;//database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        toolbar = (Toolbar) findViewById(R.id.app_bar); //Toolbar
        setSupportActionBar(toolbar); //Toolbar
        getSupportActionBar().setTitle("dashboard"); //Toolbar
        getSupportActionBar().setSubtitle("SubTitle"); //Toolbar
        getSupportActionBar().setIcon(R.drawable.ic_action_name);//Toolbar
        View gotocalendar = (View)findViewById(R.id.goToCalendarId);
        gotocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launching the calendar activity
                Intent intent = new Intent(dashboard.this, calendar.class);
                startActivity(intent);
               // finish();
            }
        });
        TextView goToSessionsBooked = (TextView) findViewById(R.id.btnGoToSeesionsBooked);
        goToSessionsBooked.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // Launching the login settings
                Intent intent = new Intent(dashboard.this, mysessions.class);
                startActivity(intent);
              //  finish();
            }
        });

        //DrawerLayout
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerOnDashboardLayout);
//        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.Open,R.string.Close);
//
//        drawerLayout.setDrawerListener(toggle);
//        toggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

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
                Intent intent = new Intent(dashboard.this, settings.class);
                startActivity(intent);
               // finish();
                break;
            case R.id.menu_2:
                logoutUser();
            break;

        }
        if(toggle.onOptionsItemSelected((item)))
        {
            return true;
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

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(dashboard.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}


