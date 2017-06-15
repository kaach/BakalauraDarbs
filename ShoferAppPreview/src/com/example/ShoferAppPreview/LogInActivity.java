package com.example.ShoferAppPreview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class LogInActivity extends Activity {
    Button btn;
    EditText parole, id;
    private Context c;
    public static String IP = "192.168.0.105";
    public static String fullLink = "http://"+IP+"/android/";
    public static ArrayList<String> parametri = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        c = getBaseContext();

        id = (EditText) findViewById(R.id.editText2);
        parole = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    public void startMarsruti(View view){
        if(!id.getText().toString().equals("")){
            parametri.add(id.getText().toString());
            new readIdsActivity(){
                @Override
                protected void onPostExecute(String result){
                    if(result.equals(parole.getText().toString())){
                        Intent i = new Intent(c,ReadMarsrutiActivity.class);
                        startActivity(i);
                    }
                    else{
                        if(parole.getText().equals(""))
                            Toast.makeText(c,"Ievadi paroli",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(c, "Pazudis interneta savienojums vai nepareizs id/parole", Toast.LENGTH_LONG).show();
                    }
                }
            }.execute(id.getText().toString());
        }
        else
            Toast.makeText(c,"Ievadi šofera ID",Toast.LENGTH_LONG);
    }

}
