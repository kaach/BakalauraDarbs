package Default;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Katrina on 14.1.5.
 */
public class ReadMarsrutiActivity extends Activity {
    Spinner spin2;
    Button btn2;
    public ArrayList<String> nosaukumi = new ArrayList<String>();
    public ArrayList<String> marsruts_ids=new ArrayList<String>();
    String temp="";
    private Context c;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marsruta_izvele);
        Intent intent = getIntent();
        c = getBaseContext();

        spin2 = (Spinner) findViewById(R.id.spinnerMarsruts);
        btn2 = (Button) findViewById(R.id.buttonJaunsReiss);

        new readMarsruti(){
            @Override
            protected void onPostExecute(String result){
                ReadMarsrutiActivity.this.readMarsruti(result);
            }
        }.execute("");
    }

    public void readMarsruti(String result) {
        temp=result;
        String[] marsruti = result.split("<br>");
        for(String s:marsruti){
            String[] idAndName = s.split("!!!");
            nosaukumi.add(idAndName[1]);
            marsruts_ids.add(idAndName[0]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item, nosaukumi);
        spin2.setAdapter(adapter);
    }


    public void addReiss(View view){
        LogInActivity.parametri.add(marsruts_ids.get(spin2.getSelectedItemPosition()));
        new insertReiss(){
            @Override
            protected void onPostExecute(String result){
                //if(!result.equals("")){
                    LogInActivity.parametri.add(result);
                    Intent i2 = new Intent(c,AddPointsActivity.class);
                    startActivity(i2);
                //}
            }
        }.execute(LogInActivity.parametri.get(1),LogInActivity.parametri.get(0));

    }
}
