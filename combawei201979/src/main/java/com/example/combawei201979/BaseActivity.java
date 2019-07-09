package com.example.combawei201979;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initData();
        initUser();
    }

    protected abstract void initUser();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int initLayout();
}
