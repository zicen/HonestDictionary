package com.lizhenquan.honestdictionary.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.lizhenquan.honestdictionary.R;
import com.lizhenquan.honestdictionary.view.activity.BaiduTranslateActivity;
import com.lizhenquan.honestdictionary.view.activity.BiyingTranslateActivity;

/**
 * Created by lizhenquan on 2017/2/13.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {


    private Button mBtn_baidu_translate;
    private Button mBtn_biying_translate;

    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        mBtn_baidu_translate = (Button)view.findViewById(R.id.btn_baidu_translate);
        mBtn_biying_translate = (Button)view.findViewById(R.id.btn_biying_translate);
        mBtn_baidu_translate.setOnClickListener(this);
        mBtn_biying_translate.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_baidu_translate:
                startActivity(new Intent(getActivity(),BaiduTranslateActivity.class));
                break;
            case R.id.btn_biying_translate:
                startActivity(new Intent(getActivity(),BiyingTranslateActivity.class));
                break;

        }

    }
}
