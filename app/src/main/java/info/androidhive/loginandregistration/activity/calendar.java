package info.androidhive.loginandregistration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.androidhive.loginandregistration.R;

public class calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
     getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
