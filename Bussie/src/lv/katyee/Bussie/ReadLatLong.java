package lv.katyee.Bussie;

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.14.5
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public class ReadLatLong extends AsyncTask<String,Void,String> {
    StringBuffer buffer;
    String line;
    public ReadLatLong(){
        buffer = new StringBuffer("") ;
        line="";
    }
    @Override
    protected String doInBackground(String... args) {
        String id = args[0];
        String date = args[1];
        String link = StartActivity.fullLink+"getLatLong.php?laiks_id="+id+"&datums="+date;
        try {
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            while((line = in.readLine())!=null){
                buffer.append(line);
                break;
            }
            in.close();
            return buffer.toString();
        } catch (Exception e) {
            return new String ("Exception:"+e.getMessage());
        }
    }
}
