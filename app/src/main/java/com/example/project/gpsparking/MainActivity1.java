package com.example.project.gpsparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity1 extends AppCompatActivity {
    ImageView i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);




        i1=(ImageView)findViewById(R.id.imageView2);

        i2=(ImageView)findViewById(R.id.imageView);

        i1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {




                Intent i=new Intent(MainActivity1.this,Setusermapdetails.class);
                startActivity(i);

            }

        });


        i2.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {


                // mechanic

                Intent i=new Intent(MainActivity1.this,Adminlogin.class);
                startActivity(i);

            }

        });


    }




}
