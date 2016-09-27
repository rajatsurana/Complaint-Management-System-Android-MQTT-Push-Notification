package com.rajat.compmsys.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rajat.compmsys.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2/20/2016.
 */
public class customlistadapter extends ArrayAdapter<Listobject> {

    private int layout;
    ArrayList<Listobject> data;

    public customlistadapter(Context context, int resource, ArrayList<Listobject> data) {
        super(context, resource,data);
        layout = resource;
        this.data=data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,parent,false);

            ViewHolder_grade viewHolder = new ViewHolder_grade();
            viewHolder.first = (TextView) convertView.findViewById(R.id.first);
            viewHolder.second = (TextView) convertView.findViewById(R.id.second);
            viewHolder.first.setText(data.get(position).first);
            viewHolder.second.setText(data.get(position).second);
            convertView.setTag(viewHolder);
        return convertView;
    }
}

class ViewHolder_grade{
    TextView first;
    TextView second;
}
