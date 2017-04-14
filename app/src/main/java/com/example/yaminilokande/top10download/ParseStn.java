package com.example.yaminilokande.top10download;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by yaminilokande on 3/20/17.
 */

//PraseStations = ParseStn and Stations = Stn .. ok

public class ParseStn {
    private static final String TAG = "ParseStn";
    //logt does the above

    private ArrayList<Stn> stns;
    private ArrayList<Times> times;
    public ParseStn() {
        this.stns = new ArrayList<>();
        this.times = new ArrayList<>();
    }

    public ArrayList<Stn> getStns() {
        return stns;
    }
    public ArrayList<Times> getTimes() {
        return times;
    }
    public boolean parse(String xmlData){
        boolean status =true;
        Stn currentRecord= null;
        Times timesRecord = null;
        boolean inEntry = false;
        boolean inEntry1 = false;//processing entry or not
        String textValue ="";
        String textValue1="";

        //declare variable for attributes of item tag
//        String destination = "";
//        String abbrev = "";

        ArrayList<String> minutes= new ArrayList<>();
        ArrayList<String> platform= new ArrayList<>();


        try{
            XmlPullParserFactory factory =XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp =factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));


            int eventType = xpp.getEventType();

            while(eventType!=XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();

                switch (eventType){
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: Starting tag for" + tagName);

                        if("etd".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentRecord = new Stn();
                            //   **************************************************************************************
                        }


                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Ending tag for"+tagName);
                        if(inEntry){
                            if("etd".equalsIgnoreCase(tagName)) {
                                stns.add(currentRecord);
                                inEntry = false;
                            }else if("destination".equalsIgnoreCase(tagName)){
                                currentRecord.setDestination(textValue);

                            }else if("abbreviation".equalsIgnoreCase(tagName)){
                                currentRecord.setAbbrev(textValue);

                            }
                        }
                        break;

                    default:
                        //nothing to do wait
                }
                eventType =xpp.next();
            }

            //**************************************
            XmlPullParserFactory factory2 =XmlPullParserFactory.newInstance();
            factory2.setNamespaceAware(true);
            XmlPullParser xpp1 =factory2.newPullParser();
            xpp1.setInput(new StringReader(xmlData));

            int eventType2 = xpp1.next();

            while(eventType2 != XmlPullParser.END_DOCUMENT){
                String tagName1=xpp1.getName();

                switch (eventType2) {
                    case XmlPullParser.START_TAG:
                        if ("etd".equalsIgnoreCase(tagName1)) {
                            inEntry1 = true;
                            timesRecord = new Times();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue1=xpp1.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry1) {
                            if ("estimate".equalsIgnoreCase(tagName1)) {
                                times.add(timesRecord);
                                inEntry1 = false;
//                                                currentRecord.setPlatform(platform);
//                                                currentRecord.setMinutes(minutes);
                            } else if ("minutes".equalsIgnoreCase(tagName1)) {
//                                minutes.add(textValue1);
                                timesRecord.setMinute(textValue1);
////                                currentRecord.setMinutes(textValue1);

                            } else if ("platform".equalsIgnoreCase(tagName1)) {
                                timesRecord.setPlatform(textValue1);
//                                platform.add(textValue1);
////                                currentRecord.setPlatform(textValue1);

                            }
                        }

                        break;

                }
//                timesRecord.setPlatform(platform);
//                timesRecord.setMinute(minutes);
                eventType2 = xpp1.next();


            }

            for(Stn app:stns){
                Log.d(TAG, "********************");
                Log.d(TAG, app.toString());
            }

            for(Times app1:times){
                Log.d(TAG, "********************");
                Log.d(TAG, app1.toString());
            }
        }catch(Exception e){
            status = false;
            e.printStackTrace();
        }



//                                                currentRecord.setPlatform(platform);
//                                                currentRecord.setMinutes(minutes);


        return status;
    }
}






