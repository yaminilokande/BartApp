package com.example.yaminilokande.top10download;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by yaminilokande on 3/20/17.
 */

public class ParseStations {

    private static final String TAG = "ParseStations";
    //logt does the above

    private ArrayList<Stations> stations;
    public ParseStations() {
        this.stations = new ArrayList<>();
    }

    public ArrayList<Stations> getStations() {
        return stations;
    }

    public boolean parse(String xmlData){
        boolean status =true;
        Stations currentRecord= null;
        boolean inEntry = false; //processing entry or not
        String textValue ="";

        //declare variable for attributes of item tag
        String trainHeadStn = "";
        String origTime = "";
        String destTime = "";

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
                        if("station".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentRecord = new Stations();
                        }
//                        else if("item".equalsIgnoreCase(tagName) && inEntry){
//
//                            //add value from xml pull parse(xpp) to variables from xml
//                            trainHeadStn = xpp.getAttributeValue(null, "trainHeadStation");
//                            origTime = xpp.getAttributeValue(null, "origTime");
//                            destTime = xpp.getAttributeValue(null, "destTime");
//
//                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Ending tag for"+tagName);
                        if(inEntry){
                            if("station".equalsIgnoreCase(tagName)){
                                stations.add(currentRecord);
                                inEntry=false;

                            }else if("name".equalsIgnoreCase(tagName)){
                                currentRecord.setName(textValue);
                            }else if("abbr".equalsIgnoreCase(tagName)){
                                currentRecord.setAbbr(textValue);
                            } else if("city".equalsIgnoreCase(tagName)){
                                currentRecord.setCity(textValue);
                            }

                        }
                        break;

                    default:
                        //nothing to do
                }
                eventType =xpp.next();

            }

            for(Stations app:stations){
                Log.d(TAG, "********************");
                Log.d(TAG, app.toString());

            }
        }catch(Exception e){
            status = false;
            e.printStackTrace();
        }

        return status;


    }
}






