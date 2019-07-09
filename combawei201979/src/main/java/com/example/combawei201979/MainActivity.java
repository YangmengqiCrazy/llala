package com.example.combawei201979;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.adapter.MyAdapter;
import com.example.bean.MyBean;
import com.example.inFace.MainFace;
import com.example.p.MainPP;
import com.example.until.Constant;
import com.example.until.HttpUntil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainFace {
    private PullToRefreshGridView ptfgrid_view;
    private HttpUntil httpUntil;
    private MainPP mainPP;

    @Override
    protected void initUser() {

    }

    @Override
    protected void initData() {
        httpUntil = HttpUntil.getHttpUntil();
        mainPP = new MainPP();
        mainPP.AttachView();
        mainPP.LoadToFromView();

        boolean isNetWork = HttpUntil.IsNetWork(this);
        if (isNetWork!=true){
            Toast.makeText(this, "有网络", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
        }
        httpUntil.AsnycTaskString(Constant.BASE_URL, new HttpUntil.CallTaskString() {
            @Override
            public void GetSString(String s) {
                Gson gson = new Gson();
                MyBean myBean = gson.fromJson(s, MyBean.class);
                List<MyBean.ResultBean> result = myBean.getResult();
                Log.i("aaa", "GetSString: "+result);
                MyAdapter adapter = new MyAdapter((ArrayList<MyBean.ResultBean>) result, MainActivity.this);
                ptfgrid_view.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void initView() {
    ptfgrid_view = findViewById(R.id.ptfgrid_view);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void ok(String data) {

    }

    @Override
    public void sucess(int code, String msg) {

    }
}
