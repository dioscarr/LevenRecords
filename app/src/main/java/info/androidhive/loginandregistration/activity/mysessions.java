package info.androidhive.loginandregistration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import info.androidhive.loginandregistration.R;
import info.androidhive.loginandregistration.helper.BuckyDB;

import static java.lang.String.valueOf;

public class mysessions extends AppCompatActivity {
BuckyDB dbHandler;
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



    }
}
