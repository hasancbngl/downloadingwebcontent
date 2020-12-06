package com.cobanogluhasan.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection=null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(in);

                int data = inputStreamReader.read();

                while(data != -1) {

                    char current = (char)  (data);

                    result = result + current;

                    data = inputStreamReader.read();

                }

                return result;

            } catch (Exception e) {

                e.printStackTrace();
                return "failed";
            }

        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask downloadTask = new DownloadTask();
        String result = null; // can be add many parameters

        try {

            result = downloadTask.execute("https://twitter.com/hasancbngl").get();

        } catch (ExecutionException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        Log.i("contents of url: ", result);



    }
}
