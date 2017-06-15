package Default;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Katrina on 14.2.5.
 */
public class AddPointsActivity extends Activity {
    TextView s,m,r;
    Button b;
    Handler handler;
    int i=0,j=0;
    private Context c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_points);
        c = getBaseContext();

        Intent intent = getIntent();

        s = (TextView) findViewById(R.id.soferaid);
        m = (TextView) findViewById(R.id.marsrutaid);
        r = (TextView) findViewById(R.id.reissid);

        b = (Button) findViewById(R.id.buttonStartPoints);

        handler = new Handler();


    }

    @Override
    public void onStart(){
        super.onStart();
        s.setText(s.getText()+LogInActivity.parametri.get(0));
        m.setText(m.getText()+LogInActivity.parametri.get(1));
        r.setText(r.getText()+LogInActivity.parametri.get(2));
    }

    public void startPoints(View view){
        handler.postDelayed(updateTimerThread,0);

    }

    private Runnable updateTimerThread = new Runnable(){

        @Override
        public void run() {
            Toast.makeText(c, "SkaitÄ«tÄ�ji: " + i + " un " + j, Toast.LENGTH_SHORT).show();
            i++;
            j++;
            handler.postDelayed(this,2000);
        }
    };
}
