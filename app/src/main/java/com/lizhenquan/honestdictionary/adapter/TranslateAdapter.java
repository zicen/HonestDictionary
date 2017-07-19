package com.lizhenquan.honestdictionary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lizhenquan.honestdictionary.R;
import com.lizhenquan.honestdictionary.bean.TranslateItemBean;

import java.util.ArrayList;

public class TranslateAdapter extends RecyclerView.Adapter<TranslateAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TranslateItemBean> items;

    public TranslateAdapter(ArrayList<TranslateItemBean> items, Context mContext) {
        this.items = items;
        context = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listitem_translate, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_request.setText(items.get(position).getRequest());
        holder.txt_result.setText(items.get(position).getResult());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_request;
        public TextView txt_result;


        public ViewHolder(View view) {
            super(view);
            txt_request = (TextView) view.findViewById(R.id.txt_request);
            txt_result = (TextView) view.findViewById(R.id.txt_result);
        }
    }

}
