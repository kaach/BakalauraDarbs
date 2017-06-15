package com.example.ShoferApp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MyActivity extends Activity {
    public Button b;
    public TextView text;
    public Button exit;
    public int counter=0;
    public Handler handler;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        b = (Button) findViewById(R.id.button);
        text = (TextView)findViewById(R.id.textView);
        exit = (Button)findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                text.setText("" + counter);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        handler = new Handler();
        handler.postDelayed(updateTimerThread,0);
    }

    public void onStart(){
        super.onStart();
    }

    private Runnable updateTimerThread = new Runnable(){

        @Override
        public void run() {
            b.performClick();  //atkartotais kods
            handler.postDelayed(this,2000);
        }
    };



}


