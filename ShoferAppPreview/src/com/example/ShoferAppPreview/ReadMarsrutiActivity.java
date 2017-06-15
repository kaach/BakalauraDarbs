package com.example.ShoferAppPreview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Katrina on 14.1.5.
 */
public class ReadMarsrutiActivity extends Activity {
    Spinner spin2, laiksSpin;
    Button btn2, btnLaiks;
    public ArrayList<String> nosaukumi = new ArrayList<String>();
    public ArrayList<String> marsruts_ids=new ArrayList<String>();
    public ArrayList<String> laikiIds;
        public ArrayList<String> laiki2;
    private Context c;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marsruta_izvele);
        c = getBaseContext();

        spin2 = (Spinner) findViewById(R.id.spinnerMarsruts);
        laiksSpin = (Spinner) findViewById(R.id.laiksSpin);
        btn2 = (Button) findViewById(R.id.buttonJaunsReiss);
        btnLaiks = (Button) findViewById(R.id.buttonLaiks);

        new readMarsruti(){
            @Override
            protected void onPostExecute(String result){
                ReadMarsrutiActivity.this.readMarsruti(result);
            }
        }.execute("");
    }

    public void readMarsruti(String result) {
        String[] marsruti = result.split("<br>");
        for(String s:marsruti){
            String[] idAndName = s.split("!!!");
            nosaukumi.add(idAndName[1]);
            marsruts_ids.add(idAndName[0]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item, nosaukumi);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter);
    }

    public void addLaiks(View view){
        laikiIds= new ArrayList<String>();
        laiki2 = new ArrayList<String>();
        new readLaiki(){
            protected void onPostExecute(String result){
                String[] laiki = result.split("<br>");
                for(String s:laiki){
                    String[] idAndLaiks = s.split("!!!");
                    laiki2.add(idAndLaiks[1]);
                    laikiIds.add(idAndLaiks[0]);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(c,R.layout.simple_spinner_item,laiki2);
                arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                laiksSpin.setAdapter(arrayAdapter);
                laiksSpin.setVisibility(View.VISIBLE);
            }
        }.execute(marsruts_ids.get(spin2.getSelectedItemPosition()));
    }


    public void addReiss(View view){
        LogInActivity.parametri.add(laikiIds.get(laiksSpin.getSelectedItemPosition()));
        new insertReiss(){
            @Override
            protected void onPostExecute(String result){
                LogInActivity.parametri.add(result);
                Intent i2 = new Intent(c,AddPointsActivity.class);
                startActivity(i2);
            }
        }.execute(LogInActivity.parametri.get(1),LogInActivity.parametri.get(0));

    }
}
