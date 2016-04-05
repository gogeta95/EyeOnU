package com.project.eyeonu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class OptionsScreen extends AppCompatActivity {

    int count = 0;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(Html.fromHtml("<font color='white'>Menu</font>"));

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsScreen.this, MainActivity.class));
            }
        });


    }


    public void news(View v) {
        Intent it = new Intent(getApplicationContext(), Newsmenu.class);
        startActivity(it);
    }


    public void email(View v) {
        Intent it = new Intent(getApplicationContext(), Email.class);
        startActivity(it);
    }


    public void track(View v) {
        Intent it = new Intent(getApplicationContext(), Navdrawer.class);
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
        getMenuInflater().inflate(R.menu.options_screen, menu);

        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(b1);
        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(b2);
        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(b3);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
