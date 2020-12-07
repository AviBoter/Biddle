package Utils;

import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TimePicker;

public class SetYourTime implements TimePickerDialog.OnTimeSetListener {

    private int hour ;
    private int minute ;
    private EditText edittext;

    public SetYourTime(EditText ed){
        this.edittext = ed;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = "";
        String min = minute < 10 ? "0" + minute : ""+minute;
        String hour = hourOfDay < 10 ? "0" + hourOfDay : ""+hourOfDay;

        time = hour + ":" + min;

        this.hour = hourOfDay;
        this.minute = minute;

        this.edittext.setText(time);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
