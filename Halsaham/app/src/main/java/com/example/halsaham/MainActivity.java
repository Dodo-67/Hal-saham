package com.example.halsaham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    private static final String TAG = "MapViewDemoActivity";
    //HUAWEI map
    private HuaweiMap hMap;

    private MapView mMapView;

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = findViewById(R.id.mapView);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        MapsInitializer.setApiKey("CgB6e3x94V79GS8I2N8LvhY3ug9VC+zJTJ14dGK50xcNflCrOoaJsIhAIivGTeJpxO9/bIu75SSKor8c8SEWPfYg");
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);


    }

    @Override
    public void onMapReady(HuaweiMap map) {
        Log.d(TAG, "onMapReady: ");
        hMap = map;
        hMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.015137, 28.979530), 10));
        hMap.addMarker(new MarkerOptions().position(new LatLng(38.43517288455261, 27.178001754806342)).title("İzmir Atatürk Stadyumu").clusterable(true));
        hMap.setMarkersClustering(true);
        hMap.setOnMarkerClickListener(new HuaweiMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("anahtar", marker.getTitle());
                startActivity(intent);
                return false;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private Marker mMarker;

    public void addMarker(View view) {
        if (null != mMarker) {
            mMarker.remove();
        }
        MarkerOptions options = new MarkerOptions()
                .position(new LatLng(41.27093970823014, 41.27093970823014))
                .title("")
                .snippet("");

        mMarker = hMap.addMarker(options);
    }
}
