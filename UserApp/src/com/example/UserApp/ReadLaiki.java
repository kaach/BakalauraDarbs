package com.example.UserApp;

/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.12.5
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;


public class ReadLaiki extends AsyncTask<String,Void,String> {
    StringBuffer buffer;
    String line;
    public ReadLaiki(){
        buffer = new StringBuffer("") ;
        line="";
    }
    @Override
    protected String doInBackground(String... args) {
        String id = args[0];
        String link = StartActivity.fullLink+"getLaiki.php?marsruts_id="+id;
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

