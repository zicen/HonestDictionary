package com.lizhenquan.honestdictionary.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lizhenquan.honestdictionary.R;
import com.lizhenquan.honestdictionary.bean.BaiduBean;
import com.lizhenquan.honestdictionary.demo.MD5;
import com.lizhenquan.honestdictionary.utils.Constans;
import com.lizhenquan.honestdictionary.utils.RetrofitUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @Bind(R.id.sp_from)
    Spinner      mSpFrom;
    @Bind(R.id.iv_change)
    ImageView    mIvChange;
    @Bind(R.id.sp_to)
    Spinner      mSpTo;
    @Bind(R.id.toorbar)
    Toolbar      mToorbar;
    @Bind(R.id.et_word)
    EditText     mEtWord;
    @Bind(R.id.btn_search)
    Button       mBtnSearch;
    @Bind(R.id.tv_result)
    TextView     mTvResult;
    @Bind(R.id.activity_second)
    LinearLayout mActivitySecond;
    @Bind(R.id.tv_result2)
    TextView     mTvResult2;
    private String sp_from[] = {"auto","en","zh"};
    private String sp_to[] = {"en","zh"};
    private String fromLan ;
    private String toLan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mBtnSearch.setOnClickListener(this);
      mSpTo.setOnItemSelectedListener(this);
        mSpFrom.setOnItemSelectedListener(this);
        mIvChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_search:
                String text = mEtWord.getText().toString();
                search(text);

            break;
            case R.id.iv_change:
                if (!TextUtils.equals(fromLan, "auto")) {
                    int selectedItemPosition = mSpTo.getSelectedItemPosition();
                    int selectedItemPosition1 = mSpFrom.getSelectedItemPosition();
                    mSpFrom.setSelection(selectedItemPosition+1);
                    mSpTo.setSelection(selectedItemPosition1-1);
                }
                break;
            default:
                break;

        }

    }
    private Map<String, String> buildParams(String query, String from, String to) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", Constans.APPID);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = Constans.APPID + query + salt + Constans.KEY; // 加密前的原文
        params.put("sign", MD5.md5(src));
        return params;
    }
    private void search(String text) {
        Map<String, String> map1 = null;
        try {
            map1 = buildParams(text, fromLan,toLan);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RetrofitUtil.getAPIRetrofitInstance2().getBaiduJsonString(map1).enqueue(new Callback<BaiduBean>() {
                @Override
                public void onResponse(Call<BaiduBean> call, Response<BaiduBean> response) {
                    if (response.isSuccessful()) {
                        BaiduBean body = response.body();
                        List<BaiduBean.TransResultBean> trans_result = body.getTrans_result();
                        if (trans_result != null) {
                            for (int i = 0; i < trans_result.size(); i++) {
                                BaiduBean.TransResultBean transResultBean = trans_result.get(i);
                                String dst = transResultBean.getDst();
                                String src = transResultBean.getSrc();
                                mTvResult.setText("源语言："+"\n"+src);
                                mTvResult2.setText("翻译："+"\n"+dst);
                            }
                        } else {
                            Toast.makeText(SecondActivity.this, "请输入后再进行查询！", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<BaiduBean> call, Throwable t) {
                    Log.d("error", t.toString());
                }
            });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {

            case R.id.sp_from:
                fromLan =  sp_from[i];
            break;
            case R.id.sp_to:
                toLan =  sp_to[i];
                break;
            default:
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
