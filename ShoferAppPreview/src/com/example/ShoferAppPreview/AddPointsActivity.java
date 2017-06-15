package com.example.ShoferAppPreview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Katrina on 14.2.5.
 */
public class AddPointsActivity extends Activity {
    TextView s,l,r;
    Button b, b2;
    Handler handler;
    String lat="",lon="";
    private Context c;
    boolean cancel = false;
    private GPSTracker gps = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_points);
        c = getBaseContext();

        s = (TextView) findViewById(R.id.soferaid);
        l = (TextView) findViewById(R.id.marsrutaid);
        r = (TextView) findViewById(R.id.reissid);

        b = (Button) findViewById(R.id.buttonStartPoints);
        b2 = (Button) findViewById(R.id.stopButton);

        handler = new Handler();
        gps = new GPSTracker(c);
    }

    @Override
    public void onStart(){
        super.onStart();
        s.setText(s.getText()+LogInActivity.parametri.get(0));
        l.setText(l.getText()+LogInActivity.parametri.get(1));
        r.setText(r.getText()+LogInActivity.parametri.get(2));
    }

    public void startPoints(View view){
        if(gps.canGetLocation()){
            cancel=false;
            handler.postDelayed(updateTimerThread,0);
        }
        else
            gps.showSettingsAlert();
    }

    public void stopPoints(View view){
        cancel=true;
        gps.stopUsingGPS();

    }

    private Runnable updateTimerThread = new Runnable(){
        @Override
        public void run() {
            gps = new GPSTracker(c);
            lat =""+gps.getLatitude();
            lon = ""+gps.getLongitude();
            new insertPunkts(){
                @Override
                protected void onPostExecute(String result){
                }
            }.execute(LogInActivity.parametri.get(2),lat,lon);
            if(!cancel)
                handler.postDelayed(this,30*1000);
        }
    };
}
