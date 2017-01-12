package com.example.sam.andtest;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.LocationSource;

public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        OnMyLocationButtonClickListener
         {
         //    int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
         private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
         private boolean mPermissionDenied = false;
    private GoogleMap mMap; //操控地圖的物件
    private Marker marker_TA101;
    private Marker marker_ATT;
    private Marker marker_NS;
    private Marker marker_SE;
    private Marker marker_BX;

    private LatLng TA101;
    private LatLng ATT;
    private LatLng NS;
    private LatLng SE;
    private LatLng BX;
    int Zoom = 17;
    // 定位的東西 開始
    static final int MIN_TIME = 5000;// 位置更新條件：5000 毫秒
    static final float MIN_DIST = 5; // 位置更新條件：5 公尺
    LocationManager mgr;        // 定位總管
    LatLng currPoint; // 儲存目前的位置
    //TextView txv;
    //定位的東西 結束

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);//連結xml fragment元件 名稱是 map
        mapFragment.getMapAsync(this);//告訴
        mgr = (LocationManager) getSystemService(LOCATION_SERVICE);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) { //建置地圖時

        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));    // 將地圖縮放級數改為 17
        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        mMap.setOnMyLocationButtonClickListener(this);
        //mMap.setMyLocationEnabled(true);    // 顯示『我的位置』圖示按鈕
        enableMyLocation();
        setmap();
    }
    private void setmap(){
        BFD();//地點經緯度
        addMarkersToMap();//地點資料
        //以下2行設定中心點 縮放比
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TA101,Zoom));//畫面中心點
        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
    }
    private void BFD(){
        TA101 = new LatLng(25.033611,121.565000);
        ATT = new LatLng(25.035413,121.566185);
        NS = new LatLng(25.047732, 121.516985);
        SE = new LatLng(25.033242, 121.568056);
        BX = new LatLng(25.041888, 121.555438);
    }
    private void addMarkersToMap() {
        marker_TA101 = mMap.addMarker(new MarkerOptions()
                .position(TA101).title("台北101")
                .snippet("於1999年動工，2004年12月31日完工啟用，樓高509.2公尺。")
                .anchor(0.0f, 1.0f)//旋轉標記?
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.badge_qld)));
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))//BDF顏色控制
                .flat(true));//扁平化標記
        marker_ATT = mMap.addMarker(new MarkerOptions()
                .position(ATT).title("ATT4FUN")
                .snippet("美食的聚集地。")
                .anchor(0.0f, 1.0f)//旋轉標記?
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.badge_qld)));
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))//BDF顏色控制
                .flat(true));//扁平化標記
        marker_NS = mMap.addMarker(new MarkerOptions()
                .position(NS).title("台北車站")
                .snippet("台北火車站。")
                .anchor(0.0f, 1.0f)//旋轉標記?
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.badge_qld)));
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))//BDF顏色控制
                .flat(true));//扁平化標記
        marker_SE = mMap.addMarker(new MarkerOptions()
                .position(SE).title("信義廣場")
                .snippet("信義廣場。")
                .anchor(0.0f, 1.0f)//旋轉標記?
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.badge_qld)));
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))//BDF顏色控制
                .flat(true));//扁平化標記
        marker_BX = mMap.addMarker(new MarkerOptions()
                .position(BX).title("BOX巴克斯蛋餅")
                .snippet("BOX巴克斯蛋餅。")
                .anchor(0.0f, 1.0f)//旋轉標記?
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.badge_qld)));
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))//BDF顏色控制
                .flat(true));//扁平化標記
    }

             @Override
             public boolean onMyLocationButtonClick() {
                 return false;
             }


             private class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View infoWindow;

        MyInfoWindowAdapter() {
            infoWindow = View.inflate(MapsActivity.this, R.layout.custom_info_window, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            int logoId;
            if (marker.equals(marker_TA101)) {
                logoId = R.drawable.badge_nsw;
            } else if (marker.equals(marker_ATT)) {
                logoId = R.drawable.badge_qld;
            } else if (marker.equals(marker_NS)) {
                logoId = R.drawable.badge_sa;
            } else if (marker.equals(marker_SE)) {
                logoId = R.drawable.badge_wa;
            } else if (marker.equals(marker_BX)) {
                logoId = R.drawable.badge_egc;
            }else {
                logoId = 0;
            }

            ImageView ivLogo = ((ImageView) infoWindow
                    .findViewById(R.id.ivLogo));
            ivLogo.setImageResource(logoId);

            String title = marker.getTitle();
            TextView tvTitle = ((TextView) infoWindow
                    .findViewById(R.id.tvTitle));
            tvTitle.setText(title);

            String snippet = marker.getSnippet();
            TextView tvSnippet = ((TextView) infoWindow
                    .findViewById(R.id.tvSnippet));
            tvSnippet.setText(snippet);
            return infoWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }

    }
             private void enableMyLocation() {
                 if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                         != PackageManager.PERMISSION_GRANTED) {
                     // Permission to access the location is missing.
                     PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                             Manifest.permission.ACCESS_FINE_LOCATION, true);
                 } else if (mMap != null) {
                     // Access to the location has been granted to the app.
                     mMap.setMyLocationEnabled(true);
                 }
             }
             @Override
             public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                    @NonNull int[] grantResults) {
                 if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
                     return;
                 }

                 if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                         Manifest.permission.ACCESS_FINE_LOCATION)) {
                     // Enable the my location layer if the permission has been granted.
                     enableMyLocation();
                 } else {
                     // Display the missing permission error dialog when the fragments resume.
                     mPermissionDenied = true;
                 }
             }
             @Override
             protected void onResumeFragments() {
                 super.onResumeFragments();
                 if (mPermissionDenied) {
                     // Permission was not granted, display error dialog.
                     showMissingPermissionError();
                     mPermissionDenied = false;
                 }
             }

             /**
              * Displays a dialog with error message explaining that the location permission is missing.
              */
             private void showMissingPermissionError() {
                 PermissionUtils.PermissionDeniedDialog
                         .newInstance(true).show(getSupportFragmentManager(), "dialog");
             }

}

