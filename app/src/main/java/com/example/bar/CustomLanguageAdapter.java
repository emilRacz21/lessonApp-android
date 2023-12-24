package com.example.bar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomLanguageAdapter extends BaseAdapter {
    String[] lang;
    int[] flag;
    LayoutInflater inflater;
    CustomLanguageAdapter(String[] lang, int[] flag, Context context){
        this.lang = lang;
        this.flag = flag;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lang.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_layout, parent, false);
        }
        TextView lang1 = convertView.findViewById(R.id.langName);
        lang1.setText(lang[position]);
        ImageView langImg = convertView.findViewById(R.id.langImg);
        langImg.setBackgroundResource(flag[position]);
        return convertView;
    }
}
