package info.androidhive.loginandregistration.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import info.androidhive.loginandregistration.R;


/**
 * Created by dioscar on 8/21/2016.
 */
public class CustomBookedAdapter extends ArrayAdapter {

    public CustomBookedAdapter(Context context, String[] Booked) {
        super(context, R.layout.activity_mysessions_row, Booked);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater SessionsInflater  = LayoutInflater.from(getContext());
        View customView = SessionsInflater.inflate(R.layout.activity_mysessions_row, parent, false);

        String SingleBookedItem = (String) getItem(position);
        TextView lblBookedSession = (TextView ) customView.findViewById(R.id.lblBookedDate);
        ImageView BookedSessionIcon  = (ImageView) customView.findViewById(R.id.imgbookedsessionicon);

        lblBookedSession.setText(SingleBookedItem);
        //BookedSessionIcon.setImageResource(R.drawable.ic_calendar_check_white_48dp);
        return customView;
    }
}
