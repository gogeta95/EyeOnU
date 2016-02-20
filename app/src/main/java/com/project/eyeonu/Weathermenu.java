package com.project.eyeonu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Weathermenu extends AppCompatActivity {

    TextView tv, tv2;
    EditText ed1;
    Button b1;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weathermenu);
        b1 = (Button) findViewById(R.id.button1);
        tv = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        ed1 = (EditText) findViewById(R.id.editText1);


    }


    public void done(View v) {
        s = ed1.getText().toString();
        if (s.equals("") == true) {

            Toast.makeText(getApplicationContext(), "Enter a valid Country & City!", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Tada).duration(700).playOn(tv);
            YoYo.with(Techniques.Tada).duration(700).playOn(tv2);
        } else {

            Intent i = new Intent(getApplicationContext(), ApplicationTest.class);
            i.putExtra("city", s);
            startActivity(i);
        }
    }


    @Override
    public void onBackPressed() {
        finish();
        return;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weathermenu, menu);

        YoYo.with(Techniques.Landing).duration(1000).playOn(tv);
        YoYo.with(Techniques.Landing).duration(1000).playOn(tv2);
        YoYo.with(Techniques.Landing).duration(1000).playOn(ed1);
        YoYo.with(Techniques.Landing).duration(1000).playOn(b1);


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
