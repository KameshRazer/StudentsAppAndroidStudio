package com.example.dash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class home extends AppCompatActivity {

    Button btnmenu,circular,timetable,ecamp,so;
    RelativeLayout maincontent;
    LinearLayout mainmenu;
    Animation fromtop,frombottom;
    ImageView userpicbig;
    TextView name,rollno,nameHome,rollNoHome;
    String rollNo,userName,imageURL;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnmenu = (Button) findViewById(R.id.btnmenu);
        circular = (Button) findViewById(R.id.circular);
        timetable = (Button) findViewById(R.id.timetable);
        ecamp = (Button) findViewById(R.id.ecamp);
        so = (Button) findViewById(R.id.so);

        name = (TextView)findViewById(R.id.name);
        rollno = (TextView)findViewById(R.id.rollno);
        nameHome = (TextView)findViewById(R.id.userhome);
        rollNoHome = (TextView)findViewById(R.id.rollnohome);

        userpicbig = (ImageView)findViewById(R.id.userpicbig);

        Intent i = getIntent();
        userName =  i.getStringExtra("name_key");
        rollNo = i.getStringExtra("rollNo_key");
        imageURL = i.getStringExtra("imageUrl_key");

//        dbRef = FirebaseDatabase.getInstance().getReference().child(rollNo);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);

        maincontent = (RelativeLayout) findViewById(R.id.maincontent);
        mainmenu = (LinearLayout) findViewById(R.id.mainmenu);

        nameHome.setText(userName);
        rollNoHome.setText(rollNo);
        System.out.println("Roll No :"+rollNo);
        Picasso.with(home.this).load(imageURL)
                .resize(400,350)
                .into(userpicbig);

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(userName);
                rollno.setText(rollNo);
                maincontent.animate().translationX(0);
                mainmenu.animate().translationX(0);

                circular.startAnimation(frombottom);
                timetable.startAnimation(frombottom);
                ecamp.startAnimation(frombottom);
                so.startAnimation(frombottom);

                name.startAnimation(fromtop);
                rollno.startAnimation(fromtop);
                userpicbig.startAnimation(fromtop);

            }
        });
        maincontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincontent.animate().translationX(-735);
                mainmenu.animate().translationX(-735);
            }
        });

    }
}
