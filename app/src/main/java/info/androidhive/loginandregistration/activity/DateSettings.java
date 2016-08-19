package info.androidhive.loginandregistration.activity;

        import android.app.DatePickerDialog;
        import android.content.Context;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.Toast;

        import info.androidhive.loginandregistration.R;

/**
 * Created by dioscar on 8/18/2016.
 */
public class DateSettings implements DatePickerDialog.OnDateSetListener{

    private final Context context;

    public DateSettings(Context context){
        this.context =context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(context, "Select date: "+dayOfMonth+" / " + monthOfYear + " / "+ year, Toast.LENGTH_LONG).show();
    }
}
