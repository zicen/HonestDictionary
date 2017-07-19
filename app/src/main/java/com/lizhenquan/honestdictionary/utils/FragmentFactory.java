package com.lizhenquan.honestdictionary.utils;

import com.lizhenquan.honestdictionary.view.fragment.BaseFragment;
import com.lizhenquan.honestdictionary.view.fragment.FindFragment;
import com.lizhenquan.honestdictionary.view.fragment.HomeFragment;
import com.lizhenquan.honestdictionary.view.fragment.MineFragment;

/**
 * Created by lizhenquan on 2017/1/17.
 */

public class FragmentFactory {
    private static HomeFragment sHomeFragment ;
    private static FindFragment sFindFragment ;
    private static MineFragment sMineFragment ;
    public static BaseFragment getFragmentByPosition(int position) {
        switch (position) {
            case 0:
                if (sHomeFragment == null) {
                    sHomeFragment = new HomeFragment();
                }
                return  sHomeFragment;
            case 1:
                if (sFindFragment == null) {
                    sFindFragment = new FindFragment();
                }
                return  sFindFragment;

            case 2:

                if (sMineFragment == null) {
                    sMineFragment = new MineFragment();
                }
                return  sMineFragment;


        }
        return null;
    }
}
