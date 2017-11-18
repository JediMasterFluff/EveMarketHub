package ca.prairesunapplications.evemarkethub.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fluffy on 18/11/17.
 */

public class JsonTask extends AsyncTask<String,String,String> {

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


    }

    @Override
    protected String doInBackground(String... strings) {

        return null;
    }
}
