package com.lizhenquan.honestdictionary.adapter;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lizhenquan.honestdictionary.R;
import com.lizhenquan.honestdictionary.bean.Language;

import java.util.ArrayList;

/**
 * Adapter for displaying search results.
 */
public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Language> items;
    private DialogFragment dialogFragment;
    // Allows to remember the last item shown on screen
    private int selPosition = 0;

    public LanguageAdapter(ArrayList<Language> items, Context mContext, DialogFragment dialogFragment) {
        this.items = items;
        context = mContext;
        this.dialogFragment = dialogFragment;
    }

    public LanguageAdapter(ArrayList<Language> items, Context mContext, int position) {
        this.items = items;
        context = mContext;
        selPosition = position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();
        View v = LayoutInflater.from(context).inflate(R.layout.listitem_language, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(items.get(position).getImg_flag()).into(holder.img_flag);
        holder.txt_name.setText(items.get(position).getLanguage_name());
        holder.txt_code.setText(items.get(position).getLanguage_code());

        if (selPosition == position) {
            int backColor = context.getResources().getColor(R.color.colorPrimary);
            holder.rel_item.setBackgroundColor(backColor);
            holder.txt_name.setTextColor(context.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_flag;
        public TextView txt_name;
        public TextView txt_code;
        public RelativeLayout rel_item;

        public ViewHolder(View view) {
            super(view);
            rel_item = (RelativeLayout) view.findViewById(R.id.rel_item);
            img_flag = (ImageView) view.findViewById(R.id.img_flag);
            txt_name = (TextView) view.findViewById(R.id.txt_name);
            txt_code = (TextView) view.findViewById(R.id.txt_code);
            txt_code.setVisibility(View.GONE);
        }
    }

}
