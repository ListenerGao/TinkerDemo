package com.listenergao.tinkerdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLoadPath;
    private Button mClearPath;
    private Button mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mLoadPath = (Button) findViewById(R.id.btn_load);
        mClearPath = (Button) findViewById(R.id.btn_clear);
        mToast = (Button) findViewById(R.id.btn_toast);

        mLoadPath.setOnClickListener(this);
        mClearPath.setOnClickListener(this);
        mToast.setOnClickListener(this);
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

            case R.id.btn_toast:
                Toast.makeText(getApplicationContext(),"使用Tinker修改后的内容",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //加载补丁
    private void loadPath() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/patch_signed_7zip.apk";
        File file = new File(path);
        if (file.exists()){
            Toast.makeText(this, "补丁存在", Toast.LENGTH_SHORT).show();
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }else {
            Toast.makeText(this, "补丁不存在", Toast.LENGTH_SHORT).show();
        }
    }

    //清除补丁
    private void clearPath() {
        Tinker.with(getApplicationContext()).cleanPatch();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/patch_signed_7zip.apk";
        File file = new File(path);
        if (file.exists()){
            Toast.makeText(this, "补丁存在", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "补丁不存在", Toast.LENGTH_SHORT).show();
        }

    }
}
