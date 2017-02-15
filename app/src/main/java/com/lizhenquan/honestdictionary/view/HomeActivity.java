package com.lizhenquan.honestdictionary.view;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lizhenquan.honestdictionary.R;
import com.lizhenquan.honestdictionary.utils.FragmentFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {

    private static final String TAG = "HomeActivity";
    @Bind(R.id.fl_content)
    FrameLayout         mFlContent;
    @Bind(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @Bind(R.id.activity_home)
    LinearLayout        mActivityHome;
    @Bind(R.id.toorbar)
    Toolbar             mToorbar;
    @Bind(R.id.et_search)
    EditText            mEtSearch;
    private String[] arr = {"首页", "发现", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    /**
     * 拷贝单词数据库到本地
     */
    private void initData() {
        // 从asserts目录下拷贝文件到files
            // 获取输出流,文件存储目录:data/data/包名/files目录下，文件名相同
            // 当文件不存在的时候：才去拷贝，已经存在的不再去拷贝了。
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(getFilesDir(), "Dictionary.db");
                if (!file.exists()) {
                    AssetManager assetManager = getAssets();
                    try {
                        // 获取输入流
                        InputStream is = assetManager.open("Dictionary.db");
                        FileOutputStream fos = new FileOutputStream(file);
                        // 开始读和写
                        byte[] bys = new byte[1024];
                        int len;
                        while ((len = is.read(bys)) != -1) {
                            fos.write(bys, 0, len);
                        }
                        is.close();
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void initView() {
        setSupportActionBar(mToorbar);
        // 拿到actionbar
        ActionBar supportActionBar = getSupportActionBar();
        // 设置标题
        supportActionBar.setTitle("");
        initFragment();
        initBottomNavigationBar();
        mEtSearch.setOnClickListener(this);
    }

    private void initFragment() {
        /**
         * 解决热部署的重影BUG
         * 如果发现Activity中有缓存的老的Fragment就将其移除掉
         */
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        for (int i = 0; i < arr.length; i++) {

            Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(i + "");
            if (fragmentByTag != null) {
                Log.d(TAG, "initFragment: 发现有老的缓存Fragment" + fragmentByTag);
                fragmentTransaction.remove(fragmentByTag);
            }
        }
        fragmentTransaction.commit();

        /**
         * 默认只添加第一个Fragment
         */
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, FragmentFactory.getFragmentByPosition(0), "0")
                .commit();
    }

    private void initBottomNavigationBar() {

        BottomNavigationItem home_item = new BottomNavigationItem(R.mipmap.home, arr[0]);
        BottomNavigationItem find_item = new BottomNavigationItem(R.mipmap.find, arr[1]);
        BottomNavigationItem mine_item = new BottomNavigationItem(R.mipmap.mine, arr[2]);
        mBottomNavigationBar.addItem(home_item);
        mBottomNavigationBar.addItem(find_item);
        mBottomNavigationBar.addItem(mine_item);
        mBottomNavigationBar.setActiveColor(R.color.colorPrimary);
        mBottomNavigationBar.setInActiveColor("#9c9c9c");
        mBottomNavigationBar.initialise();
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        /**
         * 1.修改标题
         * 2.切换Fragment
         */
        BaseFragment fragment = FragmentFactory.getFragmentByPosition(position);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fl_content, fragment, position + "");
        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {
        /**
         * 隐藏未选中的Fragment
         */
        BaseFragment fragment = FragmentFactory.getFragmentByPosition(position);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(fragment).commit();
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
