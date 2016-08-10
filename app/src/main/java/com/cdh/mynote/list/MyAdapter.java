package com.cdh.mynote.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cdh.mynote.R;

import java.util.List;

/**
 * Created by cc on 16-8-9.
 */
public class MyAdapter extends BaseAdapter {
    private Stuff[] stuffs;
    private Context context;
    private int count;
    private LayoutInflater inflater;

    public MyAdapter(Context context, Stuff[] stuffs) {
        this.context = context;
        this.stuffs = stuffs;
        if(stuffs == null) count = 0;
        else count = stuffs.length;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        if(count != 0 && position > 0 && position < count) return stuffs[position];
        else return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_item, null);
        TextView info = (TextView) view.findViewById(R.id.list_item_info);
        TextView date = (TextView) view.findViewById(R.id.list_item_date);
        String s;
        if((s = stuffs[position].getInfo()) != null) info.setText(s);
        if((s = stuffs[position].getDate()) != null) date.setText(s);
        return view;
    }
}
