package com.example.yaminilokande.top10download;

/**
 * Created by yaminilokande on 3/20/17.
 */

public class Stn {
    private String destination;
    private String abbrev;
//    private String minutes;
//    private String platform;
//
//    public String getMinutes() {
//        return minutes;
//    }
//
//    public void setMinutes(String minutes) {
//        this.minutes = minutes;
//    }
//
//    public String getPlatform() {
//        return platform;
//    }
//
//    public void setPlatform(String platform) {
//        this.platform = platform;
//    }



    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }


    @Override
    public String toString() {
        return destination +" - " +
                abbrev ;
//                minutes +" - "+"minutes"+ " - "+ "platform"+"-"+platform;

    }
}

