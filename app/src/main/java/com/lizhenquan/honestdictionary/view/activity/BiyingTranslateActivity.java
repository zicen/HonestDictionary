package com.lizhenquan.honestdictionary.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lizhenquan.honestdictionary.R;
import com.lizhenquan.honestdictionary.adapter.TranslateAdapter;
import com.lizhenquan.honestdictionary.bean.Language;
import com.lizhenquan.honestdictionary.bean.TranslateItemBean;
import com.lizhenquan.honestdictionary.utils.ResourceHelper;
import com.lizhenquan.honestdictionary.wight.DividerItemDecoration;
import com.lizhenquan.honestdictionary.wight.dialog.LanguageDialogForActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BiyingTranslateActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {


    public static final String TAG = BiyingTranslateActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txt_lang)
    TextView txtLang;
    @Bind(R.id.img_exchange_lang)
    ImageView imgExchangeLang;
    @Bind(R.id.txt_lang_target)
    TextView txtLangTarget;
    @Bind(R.id.et_translate_text)
    EditText etTranslateText;
    @Bind(R.id.txt_textnumber)
    TextView txtTextnumber;
    @Bind(R.id.img_voice)
    ImageView imgVoice;
    @Bind(R.id.et_translate_result)
    EditText etTranslateResult;
    @Bind(R.id.img_voice_result)
    ImageView imgVoiceResult;
    @Bind(R.id.recycle_history)
    RecyclerView recycleHistory;
    @Bind(R.id.content_biying_translate)
    LinearLayout contentBiyingTranslate;
    private ArrayList<TranslateItemBean> mShowItem = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    TranslateItemBean obj = (TranslateItemBean) msg.obj;
                    Toast.makeText(BiyingTranslateActivity.this, getString(R.string.success) + obj.getResult(),
                            Toast.LENGTH_SHORT).show();
                    mShowItem.add(obj);
                    mAdapter.notifyDataSetChanged();
                    etTranslateResult.setText("" + obj.getResult());
                    break;
                case 2:
                    Toast.makeText(BiyingTranslateActivity.this, getString(R.string.error) + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(BiyingTranslateActivity.this, getString(R.string.error) + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private OkHttpClient okHttpClient;
    private Request request;
    private TextToSpeech tts;
    private Language srcLanguage = new Language();
    private Language dstLanguage = new Language();
    private TranslateAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biying_translate);
        ButterKnife.bind(this);

        initLanguageBean();
        initListener();
        okHttpClient = new OkHttpClient();
        tts = new TextToSpeech(this, this);
        initRecycle();
    }




    private void initRecycle() {
        mAdapter = new TranslateAdapter(mShowItem, this);
        recycleHistory.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recycleHistory.setHasFixedSize(true);
        recycleHistory.setLayoutManager(new LinearLayoutManager(this));
        recycleHistory.setAdapter(mAdapter);
    }




    private void initLanguageBean() {
        ArrayList<Language> items = ResourceHelper.getLanguageArray(this, "languages");
        if (items == null || items.size() < 3) {
            return;
        }
        srcLanguage = items.get(0);
        dstLanguage = items.get(1);
        txtLang.setText(srcLanguage.getLanguage_name());
        txtLangTarget.setText(dstLanguage.getLanguage_name());
    }

    private void initListener() {
        imgExchangeLang.setOnClickListener(this);
        txtLang.setOnClickListener(this);
        txtLangTarget.setOnClickListener(this);
        etTranslateText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_SEARCH) {
                requestTranslate(v.getText().toString().trim(), srcLanguage.getLanguage_code(), dstLanguage.getLanguage_code());
            }
            return false;
        });
        etTranslateText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                Log.e(TAG, "start:" + start);
                Log.e(TAG, "before:" + before);
                Log.e(TAG, "count:" + count);
                txtTextnumber.setText(200 - length + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imgVoice.setOnClickListener(this);
        imgVoiceResult.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_voice:
                if (tts != null && !tts.isSpeaking()) {
                    tts.setLanguage(Locale.CHINA);
                    tts.setPitch(1.0f);
                    tts.speak(etTranslateText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }


                break;
            case R.id.img_voice_result:
                if (tts != null && !tts.isSpeaking()) {
                    tts.setLanguage(Locale.US);
                    tts.setPitch(1.0f);
                    tts.speak(etTranslateResult.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }


                break;
            case R.id.txt_lang:
                LanguageDialogForActivity languageDialogForActivity = new LanguageDialogForActivity(this);
                languageDialogForActivity.setDialogCallBackListener(language -> {
                    txtLang.setText(language.getLanguage_name());
                    srcLanguage = language;
                });
                break;
            case R.id.txt_lang_target:
                LanguageDialogForActivity languageDialogForActivity2 = new LanguageDialogForActivity(this);
                languageDialogForActivity2.setDialogCallBackListener(language -> {
                    txtLangTarget.setText(language.getLanguage_name());
                    dstLanguage = language;
                });
                break;
            case R.id.img_exchange_lang:
                Language exchange = new Language();
                exchange = srcLanguage;
                srcLanguage = dstLanguage;
                dstLanguage = exchange;

                txtLangTarget.setText(dstLanguage.getLanguage_name());
                txtLang.setText(srcLanguage.getLanguage_name());
                break;


        }


    }



    private void requestTranslate(String text, String from, String to) {
        request = new Request.Builder().
                url("http://api.microsofttranslator.com/v1/Http.svc/Translate?appId=AFC76A66CF4F434ED080D245C30CF1E71C22959C&text=" + text + "&from=" + from + "&to=" + to)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.obtainMessage(3, e.toString()).sendToTarget();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String string = response.body().string();
                    Log.e(TAG, "string:" + string);
                    TranslateItemBean translateItemBean = new TranslateItemBean(text, string);
                    handler.obtainMessage(1, translateItemBean).sendToTarget();
                } else {
                    handler.obtainMessage(2, response.code()).sendToTarget();
                }
            }
        });
    }

    @Override
    public void onInit(int status) {
        Log.e(TAG, "status:" + status);
        if (status == TextToSpeech.SUCCESS) {
            Log.e(TAG, "TextToSpeech init success!");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        tts.stop();
        tts.shutdown();
    }

}
