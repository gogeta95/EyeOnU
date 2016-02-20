package com.project.eyeonu;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashScreen extends AppCompatActivity {
    ActionBar ab;
    ProfileData helper;
    SQLiteDatabase db;
    String name;
    TextView eye;
    ImageView ive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new ProfileData(this);
        db = helper.getWritableDatabase();
        name = helper.getname();
        ab = getSupportActionBar();
        if (ab != null) {
            ab.hide();
        }
        eye = (TextView) findViewById(R.id.textView1);
        ive = (ImageView) findViewById(R.id.imageView1);
        YoYo.with(Techniques.BounceIn).duration(3000).playOn(ive);
        YoYo.with(Techniques.FadeInUp).duration(3000).playOn(eye);
        Thread b = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 3 seconds
                    sleep(3 * 1000);
                    func();
                } catch (Exception e) {

                }
            }
        };

        // start thread
        b.start();
    }

    void func() {
        if (name == null) {
            Intent it = new Intent(getApplicationContext(), WelcomeScreen.class);
            startActivity(it);
        } else {
            Intent it = new Intent(getApplicationContext(), OptionsScreen.class);
            startActivity(it);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
