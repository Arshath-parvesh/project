package com.example.project.gpsparking;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class Setuserdetails extends AppCompatActivity implements LocationListener {
    SmsManager sms;
    String result=null;
    EditText e1,e2,e3,e4,e5,e6;
    String user;
    String empty="";
    String name,mail,phone,address,dest;
    int age;
    ArrayList<String>message;
    String messgae_info;
    String place_info=null;
    double lat=0.0,lon=0.0,latitude=0.0,longitude=0.0,la=0.0,lo=0,fg=0,fg1=0,radius=0;
    protected LocationManager lm;
    Button b1,b2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpark_register);

        e2=(EditText)findViewById(R.id.editText);

        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        e3=(EditText)findViewById(R.id.editText3);

        e6=(EditText)findViewById(R.id.editText6);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button);

        final SQLiteDatabase db=openOrCreateDatabase("gps", Context.MODE_PRIVATE,null);
        String q1="CREATE TABLE parkinfo(s1 TEXT ,s2 TEXT,s3 TEXT,s4 TEXT,s5 TEXT);";
        try{

            db.execSQL(q1);
        }
        catch (Exception e){
           // Toast.makeText(this,"Exception "+e,Toast.LENGTH_SHORT).show();
        }


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String insert="INSERT INTO parkinfo(s1,s2,s3,s4,s5) VALUES('"+e2.getText().toString()+"','"+e4.getText().toString()+"','"+e5.getText().toString()+"','"+e3.getText().toString()+"', '"+e6.getText().toString()+"')";
                try{
                    db.execSQL(insert);
                    Toast.makeText(getApplicationContext(), "Parking Information Upload Succesffuly", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Setuserdetails.this,Adminpage.class);
                    startActivity(i);

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Exception in inserting "+e,Toast.LENGTH_SHORT).show();
                }



            }
        });



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

        Geocoder gc = new Geocoder(this);
        //Toast.makeText(this,"Location Getting",Toast.LENGTH_SHORT).show();
        lat=location.getLatitude();
        lon=location.getLongitude();
        e2.setText(String.valueOf(lat));
        e4.setText(String.valueOf(lon));

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
                place_info=address.getLocality();
                b1.setText(result);


                e6.setText(place_info);


            }
        } catch (IOException e) {
            Log.e(TAG, "Unable connect to Geocoder", e);



        }



        // Toast.makeText(this," Physical address "+result,Toast.LENGTH_LONG).show();



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
