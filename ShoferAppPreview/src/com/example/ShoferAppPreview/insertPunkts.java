package com.example.ShoferAppPreview;

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
 * Date: 14.8.5
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */
public class insertPunkts extends AsyncTask<String,Void, String>{
    StringBuffer buffer;
    String line;
    public insertPunkts(){
        buffer = new StringBuffer("") ;
        line="";
    }
    @Override
    protected String doInBackground(String... args) {
        String reiss_id = args[0];
        String latitude = args[1];
        String longitude = args[2];
        String link = LogInActivity.fullLink+"createPunkts.php?reiss_id="+reiss_id+"&latitude="+latitude+"&longitude="+longitude;
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
