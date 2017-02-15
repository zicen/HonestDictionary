package com.lizhenquan.honestdictionary.view;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lizhenquan.honestdictionary.R;

/**
 * Created by lizhenquan on 2017/2/13.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private Toolbar mToolbar;
    private LinearLayout mLl_contant;
    private Button mBtn_second;

    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        mBtn_second = (Button) view.findViewById(R.id.btn_second);
        mBtn_second.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(),SecondActivity.class));
    }
}
