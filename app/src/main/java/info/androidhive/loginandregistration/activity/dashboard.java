package info.androidhive.loginandregistration.activity;

/**
 * Created by dionelrodriguez on 8/22/16.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import info.androidhive.loginandregistration.helper.SQLiteHandler;
import info.androidhive.loginandregistration.helper.SessionManager;

import info.androidhive.loginandregistration.R;


public class dashboard extends AppCompatActivity
{

    private Toolbar toolbar; //Toolbar
    ActionBarDrawerToggle toggle;
    private SQLiteHandler db; //database
    private SessionManager session;//database
    View btnchangeprofileImge; // change profile image
    ImageView profile_imageNav;
    ImageView profileimage;// Hold profile image
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        toolbar = (Toolbar) findViewById(R.id.app_bar); //Toolbar
        setSupportActionBar(toolbar); //Toolbar
        getSupportActionBar().setTitle("dashboard"); //Toolbar
        getSupportActionBar().setSubtitle("SubTitle"); //Toolbar
        getSupportActionBar().setIcon(R.drawable.ic_action_name);//Toolbar
        View gotocalendar = (View)findViewById(R.id.goToCalendarId);
        gotocalendar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Launching the calendar activity
                Intent intent = new Intent(dashboard.this, calendar.class);
                startActivity(intent);

            }
        });
        TextView goToSessionsBooked = (TextView) findViewById(R.id.btnGoToSeesionsBooked);
        goToSessionsBooked.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Launching the login settings
                Intent intent = new Intent(dashboard.this, mysessions.class);
                startActivity(intent);

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


        //Change profile image

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_drawer);
        View header = navigationView.getHeaderView(0);
       // TextView text = (TextView) header.findViewById(R.id.textView);
        profileimage = (ImageView) findViewById(R.id.profile_image);
        profile_imageNav = (ImageView) header.findViewById(R.id.profile_imageNay);

       btnchangeprofileImge =  header.findViewById(R.id.btnchangeProfileImage);
        btnchangeprofileImge.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(dashboard.this,"open Gallery", Toast.LENGTH_SHORT);
                openGallery1();
            }
        });

        }
           private void openGallery1()
           {
               Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
               startActivityForResult(gallery,PICK_IMAGE);
           }


        /***
         * This method is inherited from the appCompatActivity class
         * and it initialize the menu on the toolbar
         * @param menu
         * @return
         */



        @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
            Toast.makeText(dashboard.this,"open Gallery", Toast.LENGTH_SHORT);
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
        public boolean onOptionsItemSelected(MenuItem item)
        {

            switch (item.getItemId())
            {
                case R.id.menu_1:
                    // Launching the login settings
                    Intent intent = new Intent(dashboard.this, settings.class);
                    startActivity(intent);

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

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == RESULT_OK && requestCode == PICK_IMAGE)
            {
                imageUri = data.getData();

                profileimage.setImageURI(imageUri);
                profile_imageNav.setImageURI(imageUri);
            }
        }
}


