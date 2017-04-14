package com.example.yaminilokande.top10download;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by yaminilokande on 3/19/17.
 */

public class ParseApplications {
    private static final String TAG = "ParseApplications";
    //logt does the above

    private ArrayList<FeedEntry> applications;



    public ParseApplications() {
        this.applications = new ArrayList<>();
    }

    public ArrayList<FeedEntry> getApplications() {
        return applications;
    }

    public boolean parse(String xmlData){
        boolean status =true;
        FeedEntry currentRecord= null;
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
                         currentRecord = new FeedEntry();
                     }else if("item".equalsIgnoreCase(tagName) && inEntry){

                         //add value from xml pull parse(xpp) to variables from xml
                         trainHeadStn = xpp.getAttributeValue(null, "trainHeadStation");
                         origTime = xpp.getAttributeValue(null, "origTime");
                         destTime = xpp.getAttributeValue(null, "destTime");

                     }
                     break;
                 case XmlPullParser.TEXT:
                     textValue = xpp.getText();
                     break;

                 case XmlPullParser.END_TAG:
                     Log.d(TAG, "parse: Ending tag for"+tagName);
                     if(inEntry){
                         if("station".equalsIgnoreCase(tagName)){
                             applications.add(currentRecord);
                             inEntry=false;

                         }else if("name".equalsIgnoreCase(tagName)){
                             currentRecord.setName(textValue);
                         }else if("abbr".equalsIgnoreCase(tagName)){
                             currentRecord.setAbbr(textValue);
                         }else if("item".equalsIgnoreCase(tagName)){
                             currentRecord.setItem(textValue);
                         }

                     }
                     break;

                 default:
                     //nothing to do
             }
             eventType =xpp.next();

            }

            for(FeedEntry app:applications){
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
