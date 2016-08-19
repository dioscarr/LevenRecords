package info.androidhive.loginandregistration.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import info.androidhive.loginandregistration.R;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);





    }

    public void popupCalendar(View view){

        // Launch main activity
        Intent intent = new Intent(SelectionActivity.this,
                MainActivity.class);
        startActivity(intent);
    }
}
