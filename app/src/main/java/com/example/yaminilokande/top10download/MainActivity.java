package com.example.yaminilokande.top10download;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listApps;
    private Spinner spinStations;
    private Button btnSubmit;
    private String selectedAbbr;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listApps = (ListView)findViewById(R.id.xmlListView);
        spinStations=(Spinner)findViewById(R.id.spinner2);
        //listTimes = (ListView)findViewById(R.id.xmlListView1);




        Log.d(TAG, "onCreate: starting AsyncTask");
        DownloadData downloadData = new DownloadData();
        downloadData.execute("http://api.bart.gov/api/stn.aspx?cmd=stns&key=MW9S-E7SL-26DU-VV8V");
        Log.d(TAG, "onCreate: Done!");
        addListenerOnButton();
    }

    private  class DownloadData extends AsyncTask<String,Void,String> {
        private static final String TAG = "DownloadData";


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter is" + s);
//            ParseApplications parseApplications =new ParseApplications();
//            parseApplications.parse(s);
            ParseStations parseStations = new ParseStations();
            parseStations.parse(s);

//            ArrayAdapter<FeedEntry>  arrayAdapter = new ArrayAdapter<FeedEntry>(
//                    MainActivity.this, R.layout.list_item, parseApplications.getApplications()
//            );
//            ArrayAdapter<Stations> arrayAdapter =new ArrayAdapter<Stations>(
//                    MainActivity.this, R.layout.list_item, parseStations.getStations()
//            );
            ArrayAdapter<Stations> arrayAdapter = new ArrayAdapter<Stations>(
                    MainActivity.this, R.layout.simple_spinner_item, parseStations.getStations()
            );
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


//            listApps.setAdapter(arrayAdapter);
            spinStations.setAdapter(arrayAdapter);

        }

        // changed parameter of doInBack to strings
        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: starts with " + strings[0]);
            String rssFeed = downloadXML(strings[0]);
            if (rssFeed == null) {
                Log.e(TAG, "doInBackground: Error Downloading");

            }
            return rssFeed;
        }
//logd gives Log.d(TAG, "onCreate: starting AsyncTask");
//loge gives ERRORS     Log.e(TAG, "doInBackground: Error Downloading");

        private String downloadXML(String urlPath) {

            StringBuilder xmlResult = new StringBuilder();


            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                Log.d(TAG, "downloadXML: The response code was" + response);
//                InputStream inputStream = connection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader reader = new BufferedReader(inputStreamReader);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int charsRead;
                char[] inputBuffer = new char[5000];
                while (true) {

                    charsRead = reader.read(inputBuffer);
                    if (charsRead < 0) {
                        break;
                    }
                    if (charsRead > 0) {
                        xmlResult.append(String.copyValueOf(inputBuffer, 0, charsRead));
                    }

                }
                reader.close();

                return xmlResult.toString();

            } catch (MalformedURLException e) {
                Log.e(TAG, "downloadXML:Invalid URL " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "downloadXML:IO Exception reading data " + e.getMessage());
                e.printStackTrace();
            }

            return null;

        }
    }
//***********************************the second async task*********************************

        private class DownloadData1 extends AsyncTask<String, Void, String> {
            private static final String TAG = "DownloadData1";


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d(TAG, "onPostExecute: parameter is" + s);

                ParseStn parseStns = new ParseStn();
                parseStns.parse(s);

//
//                ArrayAdapter<Stn> arrayAdapter = new ArrayAdapter<Stn>(
//                        MainActivity.this, R.layout.list_item, R.id.appTextView,parseStns.getStns()
//                );
//                ArrayAdapter<Times> arrayAdapter1 =new ArrayAdapter<Times>(
//                        MainActivity.this, R.layout.list_item, R.id.textView4,parseStns.getTimes()
//                );

                CustomAdapter adapter = new CustomAdapter(
                        MainActivity.this, R.layout.list_item, parseStns.getStns(), parseStns.getTimes()
                );

                listApps.setAdapter(adapter);
                //listApps.setAdapter(arrayAdapter);
                // spinStations.setAdapter(arrayAdapter);
                //listApps.setAdapter(arrayAdapter1);

            }

            // changed parameter of doInBack to strings
            @Override
            protected String doInBackground(String... strings) {
                Log.d(TAG, "doInBackground: starts with " + strings[0]);
                String rssFeed = downloadXML(strings[0]);
                if (rssFeed == null) {
                    Log.e(TAG, "doInBackground: Error Downloading");

                }
                return rssFeed;
            }

            private String downloadXML(String urlPath) {

                StringBuilder xmlResult = new StringBuilder();


                try {
                    URL url = new URL(urlPath);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int response = connection.getResponseCode();
                    Log.d(TAG, "downloadXML: The response code was" + response);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    int charsRead;
                    char[] inputBuffer = new char[5000];
                    while (true) {

                        charsRead = reader.read(inputBuffer);
                        if (charsRead < 0) {
                            break;
                        }
                        if (charsRead > 0) {
                            xmlResult.append(String.copyValueOf(inputBuffer, 0, charsRead));
                        }

                    }
                    reader.close();

                    return xmlResult.toString();

                } catch (MalformedURLException e) {
                    Log.e(TAG, "downloadXML:Invalid URL " + e.getMessage());
                } catch (IOException e) {
                    Log.d(TAG, "downloadXML:IO Exception reading data " + e.getMessage());
                    e.printStackTrace();
                }

                return null;

            }

        }

        public void addListenerOnButton() {
            spinStations = (Spinner) findViewById(R.id.spinner2);
            btnSubmit = (Button) findViewById(R.id.button);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Spinner spinner = (Spinner) findViewById(R.id.spinner2);
                    String Text = spinner.getSelectedItem().toString();
                    selectedAbbr = Text.substring(0, 4);
//                    Toast.makeText(MainActivity.this, selectedAbbr, Toast.LENGTH_LONG).show();

                    Log.d(TAG, "onClick: starting AsyncTask");
                    DownloadData1 downloadData1 = new DownloadData1();
                    downloadData1.execute("http://api.bart.gov/api/etd.aspx?cmd=etd&orig="+selectedAbbr+"&key=MW9S-E7SL-26DU-VV8V");
                    Log.d(TAG, "onClick: Done!");

                }
            });

        }


    }


















