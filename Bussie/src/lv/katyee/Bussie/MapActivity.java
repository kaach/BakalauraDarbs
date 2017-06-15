package lv.katyee.Bussie;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.14.5
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class MapActivity extends Activity {
    private GoogleMap map=null;
    private GoogleMapOptions options;
    private LatLng BUS;
    private String time;
    private Handler handler;
    private boolean off = false;
    private Marker bus;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.map_screen);

        checkMap();
        handler = new Handler();
        readStartCoordinates();
    }

    @Override
    protected void onStart(){
        super.onStart();
        off = false;

        handler.postDelayed(updateTimerThread,0);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        off = true;
    }

    private void checkMap() {
        if (map == null) {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            if (map != null) {
                options = new GoogleMapOptions();
                options.mapType(GoogleMap.MAP_TYPE_NORMAL).compassEnabled(false).rotateGesturesEnabled(true).tiltGesturesEnabled(false);
                MapFragment.newInstance(options);
            }
        }
    }

    public void readStartCoordinates(){
        new ReadLatLong(){
            @Override
            protected void onPostExecute(String result){
                String[] latLongTime = result.split("!!!");
                BUS = new LatLng(Double.parseDouble(latLongTime[0]),Double.parseDouble(latLongTime[1]));
                time = latLongTime[2];

                bus = map.addMarker(new MarkerOptions().position(BUS).title("Autobusa atrašanās vieta"));
                bus.setSnippet("Laiks: "+time);
                map.moveCamera(CameraUpdateFactory.newLatLng(BUS));
                map.animateCamera(CameraUpdateFactory.zoomTo(16),3000,null);


            }
        }.execute(StartActivity.parametri.get(0),StartActivity.parametri.get(1));
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            new ReadLatLong(){
                @Override
                protected void onPostExecute(String result){
                    bus.remove();
                    String[] latLongTime = result.split("!!!");
                    BUS = new LatLng(Double.parseDouble(latLongTime[0]),Double.parseDouble(latLongTime[1]));
                    time = latLongTime[2];

                    bus = map.addMarker(new MarkerOptions().position(BUS).title("Autobusa atrašanās vieta"));
                    bus.setSnippet("Laiks: "+time);
                    bus.showInfoWindow();
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(BUS,16.0f));

                }
            }.execute(StartActivity.parametri.get(0),StartActivity.parametri.get(1));
            if(!off)
            handler.postDelayed(this,30*1000);
        }
    };
}
