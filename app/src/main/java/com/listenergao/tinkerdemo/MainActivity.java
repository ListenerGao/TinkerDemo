package com.listenergao.tinkerdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLoadPath;
    private Button mClearPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mLoadPath = (Button) findViewById(R.id.btn_load);
        mClearPath = (Button) findViewById(R.id.btn_clear);

        mLoadPath.setOnClickListener(this);
        mClearPath.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                loadPath();
                break;

            case R.id.btn_clear:
                clearPath();
                break;
        }
    }

    //加载补丁
    private void loadPath() {
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "");
    }

    //清除补丁
    private void clearPath() {
        Tinker.with(getApplicationContext()).cleanPatch();
    }
}
