package info.androidhive.loginandregistration.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by dioscar on 8/18/2016.
 */
public class PickerDialogs extends DialogFragment {
    int year;
    int month;
    int day;
    @Override
    public Dialog onCreateDialog(Bundle savedInstancesState)
    {
        DateSettings datesettings = new DateSettings(getActivity());
        Calendar c = Calendar.getInstance();
         year = c.get(Calendar.YEAR);
         month = c.get(Calendar.MONTH);
         day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog;
        dialog = new DatePickerDialog(getActivity(),datesettings,year,month,day);

        return dialog;
    }
}
