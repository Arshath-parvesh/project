package com.example.project.gpsparking;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class Setusermapdetails extends AppCompatActivity implements LocationListener {
    SmsManager sms, sms1;
    ImageView i1, i2, i3;
    String result = null;
    EditText e1, e2, e3, e4, e5, e6, e7, e8;
    String user, number_info, name, bgroup, ph, emg, blood_group, numberinfo, nameinfo;

    String m1,m2,place,lam1,lom2,radious_info;
    String empty = "";
    String number, mail, phone, address, dest;
    int age;
    ArrayList<String> message;
    String messgae_info;
    double lat = 0.0, lon = 0.0, latitude = 0.0, longitude = 0.0, la = 0.0, lo = 0, fg = 0, fg1 = 0, radius = 0;

    float[] results;

    Marker mCurrLocationMarker;

    protected LocationManager lm;
    private ArrayList<LatLng> locationArrayList;
    Button b1, b2, b3;
    Cursor c;
    Cursor c1;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappage);



        int count=0;


        try {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            lm.requestLocationUpdates(lm.GPS_PROVIDER, 0, 0, (LocationListener) this);
        }
        catch (SecurityException e)
        {

        }



    }



    @Override
    public void onLocationChanged(Location location) {

       // Toast.makeText(this," Physical address "+result,Toast.LENGTH_LONG).show();
        //  b1.setText(result);

        Geocoder gc = new Geocoder(this);
        //Toast.makeText(this,"Location Getting",Toast.LENGTH_SHORT).show();
        lat = location.getLatitude();
        lon = location.getLongitude();

        //Toast.makeText(this,"Location Getting"+lat,Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,"Location Getting"+lon,Toast.LENGTH_SHORT).show();
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

                sb.append(address.getAddressLine(0)).append("\n");
                sb.append(address.getFeatureName()).append("\n");
                result = sb.toString();


                Toast.makeText(this," Physical address "+result,Toast.LENGTH_LONG).show();

                SQLiteDatabase db=openOrCreateDatabase("gps", Context.MODE_PRIVATE,null);
                try{
                    if(db!=null)
                    {
                        Toast.makeText(this," Physical address "+result,Toast.LENGTH_LONG).show();
                        c=db.rawQuery("SELECT * from parkinfo",null);
                        if(c.moveToFirst())
                        {

                            Toast.makeText(getApplicationContext(),"db call",Toast.LENGTH_LONG).show();

                            do{
                                lam1=c.getString(c.getColumnIndexOrThrow("s1"));
                                lom2=c.getString(c.getColumnIndexOrThrow("s2"));
                                radious_info=c.getString(c.getColumnIndexOrThrow("s5"));

                                double loc_lat = Double.parseDouble(lam1);
                                double loc_lon = Double.parseDouble(lom2);
                                double radious = Double.parseDouble(radious_info);

                                Location.distanceBetween(loc_lat,loc_lon,lat,lon,results);

                                Toast.makeText(this,"Distance"+results[0],Toast.LENGTH_LONG).show();

                                if(results[0]<radious)
                                {
                                    fg=1;
                                    fg1=1;

                                    Toast.makeText(getApplicationContext(),"Hi user This is Free Parking Area U can Park Vehicle Here",Toast.LENGTH_LONG).show();

                                }
                                else
                                {
                                    fg=0;fg=0;

                                    Toast.makeText(getApplicationContext(),"Hi user This is No  Parking Area",Toast.LENGTH_LONG).show();
                                }



                            }while(c.moveToNext());
                        }

                    }

                 //   Toast.makeText(getApplicationContext(),"array size"+ locationArrayList.size(),Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Exception in extracting from db"+e,Toast.LENGTH_SHORT).show();
                }




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
