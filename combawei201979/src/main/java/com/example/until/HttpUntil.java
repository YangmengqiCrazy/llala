package com.example.until;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUntil {
    //单利
    public HttpUntil(){}
    public static HttpUntil httpUntil;

    public static HttpUntil getHttpUntil() {
        if (httpUntil==null){
            return new HttpUntil();
        }
        return httpUntil;
    }
    //网络判断
    public static boolean IsNetWork(Context context){
        if (context!=null){
            ConnectivityManager service = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = service.getActiveNetworkInfo();
            if (networkInfo!=null){
                networkInfo.isAvailable();
            }
        }
        return false;
    }

    //文字工具类
    public static String GetString(String s){
        try {
            //网络地址链接
            URL url = new URL(s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200){
                //截取流
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String str="";
                StringBuilder builder = new StringBuilder();
                while((str =reader.readLine())!=null){
                    builder.append(str);
                }
                return builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public interface CallTaskString{
        void GetSString(String s);
    }
    public void AsnycTaskString(String s, final CallTaskString callTaskString){
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return GetString(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                callTaskString.GetSString(s);
            }
        }.execute(s);
    }
    ////异步
   /* public void AsnycTaskString(String s, final MainFace mainFace){
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return GetString(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
               mainFace.ok(s);
            }
        }.execute(s);
    }*/



    public static Bitmap GetImage(String s){
        Bitmap bitmap = null;
        try {
            //网络地址链接
            URL url = new URL(s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200){
                //截取流
                InputStream inputStream = connection.getInputStream();
                 bitmap = BitmapFactory.decodeStream(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public interface CallTaskImage{
        void getiimage(Bitmap s);
    }

    public static void AsnycTaskImage(String s, final CallTaskImage callTaskImage){
        new AsyncTask<String, Integer, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                return GetImage(strings[0]);
            }

            @Override
            protected void onPostExecute(Bitmap s) {
                callTaskImage.getiimage(s);
            }
        }.execute(s);
    }
}
