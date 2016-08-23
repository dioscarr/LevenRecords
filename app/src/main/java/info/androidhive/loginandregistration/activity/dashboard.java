package info.androidhive.loginandregistration.activity;

/**
 * Created by dionelrodriguez on 8/22/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import info.androidhive.loginandregistration.helper.SQLiteHandler;
import info.androidhive.loginandregistration.helper.SessionManager;

import info.androidhive.loginandregistration.R;


public class dashboard extends AppCompatActivity {

    private Toolbar toolbar;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DashBoard");
        getSupportActionBar().setSubtitle("SubTitle");
        getSupportActionBar().setIcon(R.drawable.ic_action_name);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_1:
                // Launching the login settings
                Intent intent = new Intent(dashboard.this, settings.class);
                startActivity(intent);
                finish();
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

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(dashboard.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}


