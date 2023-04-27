package com.example.ontap.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ontap.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] imgs= {R.drawable.img,R.drawable.img_1};
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View iteam = LayoutInflater.from(context).inflate(R.layout.iteam_image,parent,false);
        ImageView img =iteam.findViewById(R.id.img);
        img.setImageResource(imgs[position]);
        return iteam;
    }
}
