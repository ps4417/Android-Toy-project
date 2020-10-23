package com.example.ws;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Fragment3 extends Fragment {
    SecondActivity s;
    LocationManager locationManager;
    SupportMapFragment supportMapFragment;
    MapView gmap;

    public Fragment3(SecondActivity s) {
        this.s =s;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_3, container, false);
        gmap = (MapView) v.findViewById(R.id.map);
        gmap.onCreate(savedInstanceState);
        gmap.onResume();

        MapsInitializer.initialize(getActivity().getApplicationContext());

        gmap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng latlng = new LatLng(37.504061, 126.763382);

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                googleMap.addMarker(new MarkerOptions().position(latlng).title("롤링 파스타").snippet("xxx"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));


               LatLng latlng2 = new LatLng(37.503685, 126.762262);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                googleMap.addMarker(new MarkerOptions().position(latlng2).title("피자헛 부천시청역점").snippet("xxx"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng2,15));



                LatLng latlng3 = new LatLng(37.505404, 126.763625);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                googleMap.addMarker(new MarkerOptions().position(latlng3).title("bhc 부천시청역점").snippet("xxx"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng3,15));

                LatLng latlng4 = new LatLng(37.505352, 126.765343);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                googleMap.addMarker(new MarkerOptions().position(latlng4).title("두툼바삭가츠").snippet("xxx"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng4,15));

                LatLng latlng5 = new LatLng(37.505386, 126.762747);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                googleMap.addMarker(new MarkerOptions().position(latlng5).title("식방 SIKBANG").snippet("xxx"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng5,15));

                LatLng latlng6 = new LatLng(37.504012, 126.760386);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                googleMap.addMarker(new MarkerOptions().position(latlng6).title("어반네식당").snippet("xxx"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng6,15));
            }
        });



        return v;


    } // end onCreateView






}