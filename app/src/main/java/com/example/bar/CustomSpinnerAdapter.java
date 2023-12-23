package com.example.bar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomSpinnerAdapter extends BaseAdapter {
    String[] options;
    Context context;
    LayoutInflater inflater;
    CustomSpinnerAdapter(String[] options, Context context) {
        this.options = options;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return options.length;
    }

    @Override
    public Object getItem(int position) {
        return options[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_spinner, parent, false);
        }
        TextView text = convertView.findViewById(R.id.customSpinnerAdapte);
        text.setText(options[position]);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_spinner, parent, false);
        }
        TextView text = convertView.findViewById(R.id.customSpinnerAdapte);
        text.setText(options[position]);
        return convertView;
    }
}
