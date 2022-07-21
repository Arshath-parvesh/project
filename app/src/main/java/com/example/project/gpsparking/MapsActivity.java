package com.example.project.gpsparking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.location.LocationListener;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    double lat=0.0,lon=0.0,latitude=0.0,longitude=0.0,la=0.0,lo=0,fg=0,fg1=0,radius=0;
    protected LocationManager lm;
    String result=null;
    Marker mCurrLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {








      /*  mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(11.0168745864, 76.9595150374);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Gandhipuram, Coimabtore"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);*/
    }



    @Override
    public void onLocationChanged(Location location) {

        Geocoder gc = new Geocoder(this);
        //Toast.makeText(this,"Location Getting",Toast.LENGTH_SHORT).show();
        lat=location.getLatitude();
        lon=location.getLongitude();





        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocation(
                    lat, lon, 1);
            // Toast.makeText(this," Fetchingg.....",Toast.LENGTH_LONG).show();

            //Toast.makeText(this," List size "+addressList+"  list "+addressList.size(),Toast.LENGTH_LONG).show();
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                //  Toast.makeText(this," Fetchingaxaxxeedfefefaxg....."+address.getMaxAddressLineIndex(),Toast.LENGTH_LONG).show();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                    //          Toast.makeText(this," Fetchingaxaxxaxg.....",Toast.LENGTH_LONG).show();
                }

                sb.append( address.getAddressLine(0)).append("\n");
                sb.append( address.getFeatureName()).append("\n");
                result = sb.toString();


              //  b1.setText(result);

                Toast.makeText(this," Fetchingaxaxxaxg....."+result,Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable connect to Geocoder", e);



        }


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
























}