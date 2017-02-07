package com.pfariasmunoz.mysqldbdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Created by Pablo Farias on 06-02-17.
 */

public class BackgroundTask extends AsyncTask<String , Void, String> {

    private Context mContext;

    public BackgroundTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String ... params) {
        // URL for registration
        String reg_url = "http://10.0.2.2/projects/webapp/register.php";
        String login_url = "http://10.0.2.2/projects/webapp/login.php";
        String method = params[0];
        if (method.equals("register")) {
            String name = params[1];
            String userName = params[2];
            String password = params[3];



            // Create the URLs
            try {
                String data = URLEncoder.encode("user", "UTF-8") +
                        "=" + URLEncoder.encode(name, "UTF-8") +
                        "&" + URLEncoder.encode("user_name", "UTF-8") +
                        "=" + URLEncoder.encode(userName, "UTF-8") +
                        "&" + URLEncoder.encode("user_password", "UTF-8") +
                        "=" + URLEncoder.encode(password, "UTF-8");
                URL url = new URL(reg_url);
                makeHtttpRequest(url, data);
                return "Registration Success";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(mContext, "Registration Success", Toast.LENGTH_LONG).show();
    }

    private void makeHtttpRequest(URL url, String data) {
        HttpURLConnection urlConnection = null;
        OutputStream outputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            outputStream = urlConnection.getOutputStream();
            writeToStream(outputStream, data);
            InputStream inputStream = urlConnection.getInputStream();
            inputStream.close();


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void writeToStream(OutputStream outputStream, String data) throws IOException {
        StringBuilder output = new StringBuilder();
        if (outputStream != null) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();


        }
    }
}
