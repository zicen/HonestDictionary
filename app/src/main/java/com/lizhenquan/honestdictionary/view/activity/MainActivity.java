package com.lizhenquan.honestdictionary.view.activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lizhenquan.honestdictionary.R;
import com.lizhenquan.honestdictionary.bean.JsonWordBean;
import com.lizhenquan.honestdictionary.utils.RetrofitUtil;
import com.lizhenquan.honestdictionary.wight.MyListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TextWatcher, AdapterView.OnItemClickListener {

    private static final String TAG = "MainActivity";


    @Bind(R.id.activity_main)
    ScrollView   mActivityMain;
    @Bind(R.id.toorbar)
    Toolbar      mToorbar;
    @Bind(R.id.ll_contant)
    LinearLayout mLlContant;
    @Bind(R.id.listview)
    MyListView   mListview;
    @Bind(R.id.et_search)
    EditText     mEtSearch;

    private ProgressDialog       mDialog;
    private InputMethodManager   mInputMethodManager;
    private List<String>         mData;
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(this);
    }

    private void initView() {
        setSupportActionBar(mToorbar);
        // 拿到actionbar
        ActionBar supportActionBar = getSupportActionBar();
        // 设置标题
        supportActionBar.setTitle("");
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mEtSearch.addTextChangedListener(this);
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    //TODO
                    requestIntenet(mEtSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }



    private void queryWord(String newText) {
        File file = new File(this.getFilesDir(), "Dictionary.db");
        SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        String sql = "select word from word where word like  ?";
        String[] selectionArgs = new String[]{"%" + newText + "%"};
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            String word = cursor.getString(cursor.getColumnIndex("word"));
            Log.d(TAG, "word:" + word);
            mData.add(word);
            if (mData.size() >= 10) {
                break;
            }
        }
        int size = mData.size();
        Log.d(TAG, "DATA_SIZE:" + size);
        mAdapter.notifyDataSetChanged();
        database.close();
        cursor.close();
    }


    private void showDialog(String msg) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
        }
        mDialog.setMessage(msg);
        mDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /**
     * 用第三方的一个网站获取数据
     *
     * @param word
     */
    private void requestIntenet(final String word) {
        mListview.setVisibility(View.GONE);
        showDialog("查询中...");
        RetrofitUtil.getAPIRetrofitInstance().getJsonString(word).enqueue(new Callback<JsonWordBean>() {
            @Override
            public void onResponse(Call<JsonWordBean> call, Response<JsonWordBean> response) {
                if (response.isSuccessful()) {
                    mDialog.dismiss();
                    //如果开启软键盘则关闭，如果关闭则开启
                    if (mInputMethodManager.isActive())
                    mInputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
                    JsonWordBean body = response.body();
                    List<JsonWordBean.DefsBean> defs = body.getDefs();
                    for (int i = 0; i < defs.size(); i++) {
                        TextView textView = new TextView(MainActivity.this);
                        textView.setText(defs.get(i).getPos() + "." + defs.get(i).getDef());
                        mLlContant.addView(textView);
                    }
                    List<JsonWordBean.SamsBean> sams = body.getSams();
                    for (int i = 0; i < sams.size(); i++) {
                        TextView textView = new TextView(MainActivity.this);
                        textView.setPadding(5, 5, 5, 5);
                        textView.setText(sams.get(i).getEng() + "\\n" + sams.get(i).getChn());
                        mLlContant.addView(textView);
                    }
                } else {
                    try {
                        Toast.makeText(MainActivity.this, "查询单词失败！" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonWordBean> call, Throwable t) {
                mDialog.dismiss();
                Toast.makeText(MainActivity.this, "查询单词失败！" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String s = editable.toString();
        if (s.length() < 1) {
            mData.clear();
            mAdapter.notifyDataSetChanged();
        }
        mData.clear();
        //查询本地数据库单词，并用listView展示
        mListview.setVisibility(View.VISIBLE);
        queryWord(s);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        requestIntenet(((TextView) view).getText().toString());
    }
}
