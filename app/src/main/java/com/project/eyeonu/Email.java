package com.project.eyeonu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Email extends AppCompatActivity implements OnClickListener {

    TextView tv1, tv2, tv3, tv4, tv5;
    EditText et1, et2, et3, et4, et5;
    String name, email, subject, message, message1, message2;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
//		tv6=(TextView) findViewById(R.id.textView6);
//		
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);


        b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);

    }

    private void viewtostring() {
        // TODO Auto-generated method stub
        name = et1.getText().toString();
        email = et2.getText().toString();
        subject = et3.getText().toString();
        message = et4.getText().toString();
        message1 = et5.getText().toString();

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        viewtostring();
        String address[] = {email};
        String msg = "Hey " + name + " ! " + '\n' + message + '\n' + message1 + '\n' + '\n' + "Thank You !";
        Intent in = new Intent(android.content.Intent.ACTION_SEND);
        in.putExtra(android.content.Intent.EXTRA_EMAIL, address);
        in.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        in.setType("plain/text");
        in.putExtra(android.content.Intent.EXTRA_TEXT, msg);
        startActivity(in);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weathermenu, menu);

        YoYo.with(Techniques.SlideInLeft).duration(800).playOn(tv1);
        YoYo.with(Techniques.SlideInRight).duration(800).playOn(tv2);
        YoYo.with(Techniques.SlideInLeft).duration(800).playOn(tv3);
        YoYo.with(Techniques.SlideInRight).duration(800).playOn(tv4);
        YoYo.with(Techniques.SlideInLeft).duration(800).playOn(tv5);
        YoYo.with(Techniques.SlideInRight).duration(800).playOn(et1);
        YoYo.with(Techniques.SlideInLeft).duration(800).playOn(et2);
        YoYo.with(Techniques.SlideInRight).duration(800).playOn(et3);
        YoYo.with(Techniques.SlideInLeft).duration(800).playOn(et4);
        YoYo.with(Techniques.SlideInRight).duration(800).playOn(b);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
