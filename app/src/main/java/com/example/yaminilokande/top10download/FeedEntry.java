package com.example.yaminilokande.top10download;

/**
 * Created by yaminilokande on 3/19/17.
 */

public class FeedEntry {

    private String name;
    private String abbr;
    private String item;
    //need to create a class called Item and add to it a list of station head name, times etc


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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "name=" + name + '\n' +
                ", abbr=" + abbr + '\n' +
                ", item=" + item + '\n' ;
    }
}
