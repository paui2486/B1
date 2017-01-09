package com.example.sam.andtest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


//import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
//import com.zhy.http.okhttp.OkHttpUtils;
//import com.zhy.http.okhttp.callback.BitmapCallback;


import java.util.ArrayList;
import java.util.List;

//import static android.R.attr.name;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView wv;
    ProgressBar pb;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //這個物件是上方的 條狀物
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "Fab clicked", Toast.LENGTH_LONG).show();
                 Intent tt = new Intent(getApplicationContext(), MapsActivity.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到地圖頁面
                 startActivity(tt); //實作 it
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

               // Intent tt = new Intent(this, Main3Activity.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
                // startActivity(tt); //實作 it
                //"Replace with your own action"
                //"Action"

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //讀網頁開始
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
        wv.loadUrl("https://b1.media/");  // 連到B1網站，

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }  else if (id == R.id.NAV_C) {
                Intent tt = new Intent(this, article_type_1.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
                startActivity(tt); //實作 it
        } else if (id == R.id.NAV_G) {
            Intent tt = new Intent(this, article_type_2.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
            startActivity(tt); //實作 it
        } else if (id == R.id.NAV_S) {
            Intent tt = new Intent(this, article_type_3.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
            startActivity(tt); //實作 it
        } else if (id == R.id.NAV_M) {
            Intent tt = new Intent(this, article_type_4.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
            startActivity(tt); //實作 it
        } else if (id == R.id.NAV_B) {
            Intent tt = new Intent(this, article_type_5.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
            startActivity(tt); //實作 it
        } else if (id == R.id.NAV_P) {
            Intent tt = new Intent(this, article_type_6.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
            startActivity(tt); //實作 it
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void go2(View v){//button oncheck 名稱
        Intent it = new Intent(this, Main4Activity.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
        startActivity(it); //實作 it
    }
    public void go3(View v){//button oncheck 名稱
        Intent tt = new Intent(this, Main5Activity.class);//跳頁簡單說明 先有一個目標(頁面) 在原本頁面有元件(button)動作跳轉到第2頁面
        startActivity(tt); //實作 it
    }

}
