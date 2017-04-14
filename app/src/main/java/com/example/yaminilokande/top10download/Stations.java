package com.example.yaminilokande.top10download;

/**
 * Created by yaminilokande on 3/20/17.
 */

public class Stations {


    private String name;
    private String abbr;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return abbr+" - "+ name+" - "+city;
    }
}
