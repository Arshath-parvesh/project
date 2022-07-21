package com.example.project.gpsparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Adminpage extends AppCompatActivity {

    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        b1=(Button)findViewById(R.id.b1);

        b4=(Button)findViewById(R.id.button2);




        b3=(Button)findViewById(R.id.b3);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Adminpage.this,Setuserdetails.class);
                startActivity(i);


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //view
                Intent i=new Intent(Adminpage.this,viewinfo.class);
                startActivity(i);


            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Adminpage.this,MainActivity.class);
                startActivity(i);

            }
        });






    }
}
