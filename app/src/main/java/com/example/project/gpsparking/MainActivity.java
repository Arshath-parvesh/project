package com.example.project.gpsparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        i3=(ImageView)findViewById(R.id.imageView);



        i3.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {




                Intent i=new Intent(MainActivity.this, MainActivity1.class);
                startActivity(i);
            }

        });



    }
}
