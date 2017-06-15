package lv.katyee.Bussie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntielliJ IDEA.
 * User: Katrina
 * Date: 14.14.5
 * Time: 19:35
 * To change this template use File | Settings | File Templates.
 */
public class DateActivity extends Activity {
    private Context c;
    private DatePicker datePicker;
    private int day=0;
    private int month=0;
    private int year=0;

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.date_layout);
        c = getBaseContext();

        setCurrentDate();



    }

    public void setCurrentDate(){
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, null);


    }

    public void nextActivity(View view){
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth();
        year = datePicker.getYear();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,day);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.YEAR,year);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(cal.getTime());
        StartActivity.parametri.add(date);


        Intent i = new Intent(c, MapActivity.class);
        startActivity(i);
    }

}
