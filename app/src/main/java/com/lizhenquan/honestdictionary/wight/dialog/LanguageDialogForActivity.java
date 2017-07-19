package com.lizhenquan.honestdictionary.wight.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lizhenquan.honestdictionary.R;
import com.lizhenquan.honestdictionary.adapter.LanguageAdapter;
import com.lizhenquan.honestdictionary.bean.Language;
import com.lizhenquan.honestdictionary.utils.ResourceHelper;
import com.lizhenquan.honestdictionary.utils.ScreenSizeUtils;
import com.lizhenquan.honestdictionary.wight.RecyclerItemClickListener;

import java.util.ArrayList;

import static com.lizhenquan.honestdictionary.view.activity.BiyingTranslateActivity.TAG;

/**
 * Created by zhenquan on 2017/6/26.
 */

public class LanguageDialogForActivity extends Dialog {
    private DialogCallBackListener mDialogCallBackListener;
    public LanguageDialogForActivity(Context context) {
        super(context, R.style.record_dialog);

        View view = LayoutInflater.from(context).inflate(R.layout.dlg_etlist_selectlang, null);


        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(context).getScreenHeight() * 0.23f));
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) context.getResources().getDisplayMetrics().widthPixels;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        this.show();



        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_dlg_etlist);
        ImageView imgDlgEtlistBack = (ImageView) view.findViewById(R.id.img_dlg_etlist_back);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        imgDlgEtlistBack.setOnClickListener(v -> dismiss());
        ArrayList<Language> items = ResourceHelper.getLanguageArray(context, "languages");
        LanguageAdapter adapter = new LanguageAdapter(items, context, 0);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, (View v, int position) -> {
                    Language lang = items.get(position);
                    if (mDialogCallBackListener != null) {
                        mDialogCallBackListener.onItemouch(lang);
                    }
                    Log.e(TAG, lang.toString());
                    this.dismiss();
                })
        );

        super.setContentView(view);
        this.setCanceledOnTouchOutside(true);
    }

    public interface DialogCallBackListener{
        public void onItemouch(Language language);
    }
    public void setDialogCallBackListener(DialogCallBackListener listener){
        this.mDialogCallBackListener  = listener;
    }
}
