package com.example.yaminilokande.top10download;

/**
 * Created by yaminilokande on 3/23/17.
 */

public class Times {


    private String minute;
    private String platform;

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "minutes=" + minute +
                ", platform=" + platform;
    }




}
