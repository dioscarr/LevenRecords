package info.androidhive.loginandregistration.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import info.androidhive.loginandregistration.R;

public class SelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Button btnBookaSession1 = (Button) findViewById(R.id.btnBookaSession);
        //Go to Calendar
        btnBookaSession1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                // Launch main activity
                Intent intent = new Intent(SelectionActivity.this,
                        calendar.class);
                startActivity(intent);
            }
        });
    }
    public void viewBookedSessionsonClick(View view){

        // Launch main activity
        Intent intent = new Intent(SelectionActivity.this,
                mysessions.class);
        startActivity(intent);
    }
}
