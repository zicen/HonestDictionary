package com.lizhenquan.honestdictionary.view;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.lizhenquan.honestdictionary.R;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by lizhenquan on 2017/2/13.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private Toolbar              mToolbar;
    private LinearLayout         mLl_contant;
    private FloatingActionButton mFab;

    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mFab.setOnClickListener(this);
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
