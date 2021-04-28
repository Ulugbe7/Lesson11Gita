package com.example.lesson11gita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private ArrayList<Data> list;

    private LayoutInflater inflater;

    private Context context;

    public MyAdapter(Context context, List<Data> list) {
        this.list = new ArrayList<>(list);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_adapter, null);
        }

        TextView textName = convertView.findViewById(R.id.textName);
        TextView textPhone = convertView.findViewById(R.id.textPhone);
        Data data = list.get(position);
        textName.setText(data.getName());
        textPhone.setText(data.getPhone());

        return convertView;
    }
}
