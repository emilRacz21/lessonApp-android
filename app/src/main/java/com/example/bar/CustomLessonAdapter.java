package com.example.bar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
public class CustomLessonAdapter extends BaseAdapter {
    List<LessonList> lessonList;
    LayoutInflater inflater;
    CustomLessonAdapter(List<LessonList> lessonList, Context context){
        this.lessonList = lessonList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lessonList.size();
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

        if(convertView == null){
            convertView= inflater.inflate(R.layout.custom_list_layout,parent,false);
        }
        LessonList lessonList = this.lessonList.get(position);
        TextView text = convertView.findViewById(R.id.textList);
        text.setText(lessonList.data1 +" - "+ lessonList.data2);
        return convertView;
    }
}
