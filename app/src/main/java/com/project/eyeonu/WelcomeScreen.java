package com.project.eyeonu;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity {
    ActionBar ab;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getSupportActionBar().setTitle("About Us");
    }

    protected void onDestroy() {

        super.onDestroy();

    }

    public void enterInfo(View v) {
        Intent it = new Intent(getApplicationContext(), ProfileInfo.class);
        startActivity(it);
    }

    public void onBackPressed() {
        if (count == 1) {
            count = 0;
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            count++;
        }

        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_first_screen, menu);
        return true;
    }

}
