package lv.katyee.Bussie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.14.5
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class ReadMarsrutiActivity extends Activity {
    private Context c;
    private Spinner spin, spin2;
    private Button btn;
    private ArrayList<String> ids=new ArrayList<String>();
    private ArrayList<String>nosaukumi=new ArrayList<String>();
    public ArrayList<String> laikiIds;
    public ArrayList<String> laiki2;

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.atrast_autobusu);
        c = getBaseContext();
        spin = (Spinner)findViewById(R.id.spinnerMarsruts);
        spin2 = (Spinner)findViewById(R.id.spLaiks);
        btn = (Button)findViewById(R.id.buttonLaiks);

        new ReadMarsruti(){
            @Override
            protected void onPostExecute(String result){
                if(!result.equals("Exception: No route to host"))
                    ReadMarsrutiActivity.this.readMarsruti(result);
            }
        }.execute("");
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    public void readMarsruti(String result) {
        String[] marsruti = result.split("<br>");
        for(String s:marsruti){
            String[] marsruts = s.split("!!!");
            ids.add(marsruts[0]);
            nosaukumi.add(marsruts[1]);
        }
        ArrayAdapter adapter = new ArrayAdapter(c,R.layout.simple_spinner_item,nosaukumi);
        spin.setAdapter(adapter);

    }

    public void readLaiki(View view){
        laikiIds= new ArrayList<String>();
        laiki2 = new ArrayList<String>();
        new ReadLaiki(){
            protected void onPostExecute(String result){
                String[] laiki = result.split("<br>");
                for(String s:laiki){
                    String[] idAndLaiks = s.split("!!!");
                    laiki2.add(idAndLaiks[1]);
                    laikiIds.add(idAndLaiks[0]);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(c,R.layout.simple_spinner_item,laiki2);
                spin2.setAdapter(arrayAdapter);
                spin2.setVisibility(View.VISIBLE);
            }
        }.execute(ids.get(spin.getSelectedItemPosition()));
    }

    public void toDateActivity(View view){

        StartActivity.parametri.add(laikiIds.get(spin2.getSelectedItemPosition()));
        Intent i = new Intent(c,DateActivity.class);
        startActivity(i);
    }



}
