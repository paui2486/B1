package com.example.sam.andtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//地圖頁
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    public void back(View v){//button oncheck 名稱
        Intent it = new Intent(this, MainActivity.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
        startActivity(it); //實作 it
    }
}
