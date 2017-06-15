package Default;

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
    public TextView wrong;
    private Context c;
    public static String IP = "46.109.139.93";
    public static String fullLink = "http://"+IP+"/android/";
    public static ArrayList<String> parametri = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        c = getBaseContext();

        id = (EditText) findViewById(R.id.editText2);
        parole = (EditText) findViewById(R.id.editText);
        wrong = (TextView) findViewById(R.id.textView4);
        btn = (Button) findViewById(R.id.button);

        wrong.setVisibility(View.INVISIBLE);


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
                        wrong.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(c,ReadMarsrutiActivity.class);
                        startActivity(i);

                    }
                    else{
                        if(parole.getText().equals("")){
                            Toast.makeText(c,"Ievadi paroli",Toast.LENGTH_LONG).show();
                        }
                        else
                            wrong.setVisibility(View.VISIBLE);
                    }
                }
            }.execute(id.getText().toString());
        }
        else
            Toast.makeText(c,"Ievadi ðofera ID",Toast.LENGTH_LONG);
    }

}
