package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.MyBean;
import com.example.combawei201979.R;
import com.example.until.Constant;
import com.example.until.HttpUntil;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<MyBean.ResultBean> list;
    private Context context;

    public MyAdapter(ArrayList<MyBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
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
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view== null){
            view= View.inflate(context, R.layout.activity_base, null);
            holder  = new ViewHolder();
            holder.text_price = view.findViewById(R.id.text_price);
            holder.text_commodityName = view.findViewById(R.id.text_commodityName);
            holder.imag_view = view.findViewById(R.id.imag_view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text_commodityName.setText(list.get(position).getCommodityName());
        holder.text_price.setText(list.get(position).getPrice());
        String masterPic = list.get(position).getMasterPic();
        HttpUntil.AsnycTaskImage(Constant.BASE_URL, new HttpUntil.CallTaskImage() {
            @Override
            public void getiimage(Bitmap s) {
                holder.imag_view.setImageBitmap(s);
            }
        });
        return view;
    }
    class ViewHolder{
        ImageView imag_view;
        TextView text_commodityName,text_price;
    }
}
