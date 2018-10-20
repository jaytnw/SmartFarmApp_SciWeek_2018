package com.example.jay.farm;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends Activity {

    TextView Tem,Hum;
    ImageButton btLight,btMisk;
    String chkL="ON",chkM="ON";

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference TempFarm = mRootRef.child("TempFarm");
    DatabaseReference HumFarm = mRootRef.child("HumFarm");
    DatabaseReference MiskFence = mRootRef.child("WaterFarm");
    DatabaseReference LightFarm = mRootRef.child("LightFarm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        Tem = (TextView) findViewById(R.id.tem);
        Hum = (TextView) findViewById(R.id.hum);

        btLight =(ImageButton) findViewById(R.id.btLight);
        btMisk =(ImageButton) findViewById(R.id.btMisk);

        TempFarm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String temm = dataSnapshot.getValue(String.class);
                Tem.setText(temm);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        HumFarm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String temm = dataSnapshot.getValue(String.class);
                Hum.setText(temm);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkL == "OFF" ){
                    btLight.setImageResource(R.mipmap.light);
                    LightFarm.setValue("OFF");
                    chkL = "ON";
                }else if(chkL == "ON" )  {
                    btLight.setImageResource(R.mipmap.light_click);
                    LightFarm.setValue("ON");
                    chkL = "OFF";
                }
            }
        });

        btMisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkM == "OFF" ){
                    btMisk.setImageResource(R.mipmap.misk);
                    MiskFence.setValue("OFF");
                    chkM = "ON";
                }else if(chkM == "ON" )  {
                    btMisk.setImageResource(R.mipmap.misk_click);
                    MiskFence.setValue("ON");
                    chkM = "OFF";
                }
            }
        });






    }
}
