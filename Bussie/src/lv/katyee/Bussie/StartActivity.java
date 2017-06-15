package lv.katyee.Bussie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.14.5
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class StartActivity extends Activity {
    private Context c = null;
    private Button b;
    public static String IP = "192.168.0.105";
    public static String fullLink = "http://"+IP+"/android/";
    public static ArrayList<String> parametri = new ArrayList<String>();
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.start_screen);
        c = getBaseContext();
        b = (Button)findViewById(R.id.button);
    }

    public void atrastAutobusu(View view){
        Intent i = new Intent(c,ReadMarsrutiActivity.class);
        startActivity(i);
    }
}
