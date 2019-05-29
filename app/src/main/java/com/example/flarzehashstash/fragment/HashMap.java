package com.example.flarzehashstash.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.flarzehashstash.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;


public class HashMap extends Fragment implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {


    private static final int MULTIPLE_PERMISSION_REQUEST_CODE = 4;
    View view, vi;
    Double lat, lan;
    String lati, longi;
    private RelativeLayout rootContent;
    private MapView mapView;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;


    public HashMap() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hash_map, container, false);


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        checkPermissionsState();

        return view;
    }


    private void checkPermissionsState() {
        int internetPermissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.INTERNET);

        int networkStatePermissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_NETWORK_STATE);

        int writeExternalStoragePermissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int coarseLocationPermissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);

        int fineLocationPermissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        int wifiStatePermissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_WIFI_STATE);
        int readContactPermissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_CONTACTS);

        if (internetPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                networkStatePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                writeExternalStoragePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                coarseLocationPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                fineLocationPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                wifiStatePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                readContactPermissionCheck == PackageManager.PERMISSION_GRANTED) {

            setupMap();
            // setHash(1,1);
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.READ_CONTACTS},
                    MULTIPLE_PERMISSION_REQUEST_CODE);
        }
    }

    private void setupMap() {

        /*final float scale = getContext().getResources().getDisplayMetrics().density;
        final int newScale = (int) (256 * scale);
        String[] OSMSource = new String[2];
        OSMSource[0] = "http://a.tile.openstreetmap.org/";
        OSMSource[1] = "http://b.tile.openstreetmap.org/";
        XYTileSource MapSource = new XYTileSource("OSM", , 1, 18, newScale, ".png", OSMSource);
*/

        mapView =  view.findViewById(R.id.mapview);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(false);
        //setContentView(mapView); //displaying the MapView
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(10); //set initial zoom-level, depends on your need
        //mapView.getController().setCenter(ONCATIVO);
        mapView.setUseDataConnection(true); //keeps the mapView from loading online tiles using network connection.
        //Configuration.getInstance().setUserAgentValue(getPackageName());
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setTilesScaledToDpi(true);

        MyLocationNewOverlay oMapLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), mapView);
        // mapView.getOverlays().add(oMapLocationOverlay);
        //  oMapLocationOverlay.enableFollowLocation();
        oMapLocationOverlay.enableMyLocation();
        //  oMapLocationOverlay.enableFollowLocation();

//        GeoPoint geoPoint = new GeoPoint(  mapView.getMapCenter().getLatitude(),mapView.getMapCenter().getLongitude());
//        OverlayItem overlayItem = new OverlayItem("San Fransisco", "California", geoPoint);
//        android.graphics.drawable.Drawable markerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_hash);
//        overlayItem.setMarker(markerDrawable);


        CompassOverlay compassOverlay = new CompassOverlay(getContext(), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);
////

    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
