package com.example.p;

import com.example.inFace.MainFace;
import com.example.until.HttpUntil;

public  class MainPP extends BasePP implements MainFace {
    private HttpUntil httpUntil;

    public void  MainPP(){
         httpUntil = HttpUntil.getHttpUntil();
    }
    public void AttachView(){
        this.httpUntil = httpUntil;

    }
    public void LoadToFromView(){

    }

    @Override
    public void ok(String data) {

    }

    @Override
    public void sucess(int code, String msg) {

    }
}
