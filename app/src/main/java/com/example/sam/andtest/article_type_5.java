package com.example.sam.andtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class article_type_5 extends AppCompatActivity {
    WebView wv;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_type_5);

        wv = (WebView) findViewById(R.id.wv);
        pb = (ProgressBar) findViewById(R.id.pb);
        wv.getSettings().setJavaScriptEnabled(true);	// 啟用 JavaScript
        wv.getSettings().setBuiltInZoomControls(true);	// 啟用縮放功能
        //wv.invokeZoomPicker();	                        // 顯示縮放小工具
        wv.setWebViewClient(new WebViewClient());		// 建立及使用 WebViewClient 物件
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pb.setProgress(progress);       //設定進度
                pb.setVisibility(progress < 100? View.VISIBLE: View.GONE);  //依進度來讓進度條顯示或消失
            }
        });
        wv.loadUrl("https://b1.media/archives/category/%E7%BE%8E%E5%A6%9D%E4%BF%9D%E9%A4%8A");   // 美妝
    }
}
