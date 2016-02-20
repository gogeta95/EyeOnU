package com.project.eyeonu;

import android.app.Fragment;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class WhereAmI extends Fragment {
    public static double lat = 0.0000;
    public static double longi = 0.0000;
    public static String addressString = "Address Not Available";
    public static String disp;
    GoogleMap googleMap;
    Marker marker;
    TextView tv;
    Location current;
    LocationManager lm;
    LocationListener locLis;
    LatLng Locateme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.whereami, container, false);
        tv = (TextView) v.findViewById(R.id.textView1);
        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locLis = new LocationListener() {
            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
                switch (arg1) {
                    case LocationProvider.AVAILABLE:
                        Toast.makeText(getActivity(), "AVAILABLE", Toast.LENGTH_SHORT).show();
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        Toast.makeText(getActivity(), "OUT_OF_SERVICE", Toast.LENGTH_SHORT).show();
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        Toast.makeText(getActivity(), "TEMPORARILY_UNAVAILABLE", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getActivity(), provider + " Enabled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(getActivity(), provider + " Disabled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLocationChanged(Location loc) {
                addressString = "";
                lat = loc.getLatitude();
                longi = loc.getLongitude();
                Geocoder gc = new Geocoder(getActivity());
                List<Address> address;
                try {
                    address = gc.getFromLocation(lat, longi, 1);
                    for (int i = 0; i < address.size(); i++) {
                        Address a = address.get(i);
                        for (int j = 0; j < a.getMaxAddressLineIndex(); j++) {
                            addressString += a.getAddressLine(j) + "\n";
                        }
                    }

                    disp = "Latitude: " + lat + "\n" + "Longitude: " + longi + "\n" + "Address: " + addressString;
                    tv.setText(disp);
                    YoYo.with(Techniques.FadeIn).duration(900).playOn(tv);
                    if (googleMap == null) {
                        googleMap = ((MapFragment) getFragmentManager().
                                findFragmentById(R.id.map)).getMap();
                        googleMap.setMyLocationEnabled(true);
                    }
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    marker = googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lat, longi)).title("Current Location"));
                    marker.setIcon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(lat, longi)).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory
                            .newCameraPosition(cameraPosition));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        return v;
    }

    @Override
    public void onResume() {
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 5, locLis);
        super.onResume();
    }

    @Override
    public void onPause() {
        lm.removeUpdates(locLis);
        super.onPause();
    }
}