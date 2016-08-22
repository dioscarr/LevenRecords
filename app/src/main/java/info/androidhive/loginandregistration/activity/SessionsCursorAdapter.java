package info.androidhive.loginandregistration.activity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import info.androidhive.loginandregistration.R;

/**
 * Created by dioscar on 8/21/2016.
 */
public class SessionsCursorAdapter extends CursorAdapter {
public SessionsCursorAdapter(Context context, Cursor cursor)
{
    super(context, cursor, 0);
}
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_mysessions, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
       // TextView idtxt = (TextView) view.findViewById(R.id.txtSessionID);
        //TextView sessionDate = (TextView) view.findViewById(R.id.txtSessionDate);

       // int txtid = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
       // int Datesession = cursor.getInt(cursor.getColumnIndexOrThrow("BookDate"));

       // idtxt.setText(String.valueOf(idtxt));
       // sessionDate.setText(Datesession);
    }
}
