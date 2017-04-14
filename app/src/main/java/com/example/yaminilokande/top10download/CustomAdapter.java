package com.example.yaminilokande.top10download;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yaminilokande on 3/23/17.
 */

public class CustomAdapter extends ArrayAdapter<Stn> {

    private Context context;
    private ArrayList<Stn> items;
    private ArrayList<Times> timer;

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Stn> items, ArrayList<Times> timer) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.timer = timer;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item, null);
        }
        Stn s = items.get(position);
        Times t = timer.get(position);
        if (s != null) {
            TextView tt = (TextView) v.findViewById(R.id.appTextView);
            TextView bt = (TextView) v.findViewById(R.id.textView4);
            if (tt != null) {
                tt.setText(s.toString());
            }
            if (bt != null) {
                bt.setText(t.toString());
            }
        }
        return v;
    }



}
