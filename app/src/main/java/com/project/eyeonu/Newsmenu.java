package com.project.eyeonu;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Newsmenu extends AppCompatActivity implements View.OnClickListener {

    String[] urls;
    String[] loadingTexts;
    String[] titles;
    ActionBar ab;
    int count = 0;
    Button b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsmenu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='white'>New's Menu</font>"));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        urls = getResources().getStringArray(R.array.news_urls);
        loadingTexts = getResources().getStringArray(R.array.loading_texts);
        titles = getResources().getStringArray(R.array.news_titles);
        b2 = (Button) findViewById(R.id.india);
        b2.setOnClickListener(this);
        b3 = (Button) findViewById(R.id.world);
        b3.setOnClickListener(this);
        b4 = (Button) findViewById(R.id.national);
        b4.setOnClickListener(this);
        b5 = (Button) findViewById(R.id.international);
        b5.setOnClickListener(this);
        b6 = (Button) findViewById(R.id.cricket);
        b6.setOnClickListener(this);
        b7 = (Button) findViewById(R.id.tennis);
        b7.setOnClickListener(this);
        b8 = (Button) findViewById(R.id.hockey);
        b8.setOnClickListener(this);
        b9 = (Button) findViewById(R.id.soccer);
        b9.setOnClickListener(this);
        b10 = (Button) findViewById(R.id.bollywood);
        b10.setOnClickListener(this);
        b11 = (Button) findViewById(R.id.hollywood);
        b11.setOnClickListener(this);
        b12 = (Button) findViewById(R.id.weather);
        b12.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.newsmenu, menu);

        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(b2);
        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(b3);
        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(b4);
        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(b5);
        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(b6);
        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(b7);
        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(b8);
        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(b9);
        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(b10);
        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(b11);
        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(b12);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.weather) {
            Intent it = new Intent(getApplicationContext(), Weathermenu.class);
            startActivity(it);
            return;
        }
        Intent intent = new Intent(this, RSSmain.class);
        switch (v.getId()) {
            case R.id.india:
                intent.putExtra(RSSmain.KEY_TITLE, titles[0]);
                intent.putExtra(RSSmain.KEY_URL, urls[0]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[0]);
                break;
            case R.id.world:
                intent.putExtra(RSSmain.KEY_TITLE, titles[1]);
                intent.putExtra(RSSmain.KEY_URL, urls[1]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[1]);
                break;
            case R.id.national:
                intent.putExtra(RSSmain.KEY_TITLE, titles[2]);
                intent.putExtra(RSSmain.KEY_URL, urls[2]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[2]);
                break;
            case R.id.international:
                intent.putExtra(RSSmain.KEY_TITLE, titles[3]);
                intent.putExtra(RSSmain.KEY_URL, urls[3]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[3]);
                break;
            case R.id.cricket:
                intent.putExtra(RSSmain.KEY_TITLE, titles[4]);
                intent.putExtra(RSSmain.KEY_URL, urls[4]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[4]);
                break;
            case R.id.tennis:
                intent.putExtra(RSSmain.KEY_TITLE, titles[5]);
                intent.putExtra(RSSmain.KEY_URL, urls[5]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[5]);
                break;
            case R.id.hockey:
                intent.putExtra(RSSmain.KEY_TITLE, titles[6]);
                intent.putExtra(RSSmain.KEY_URL, urls[6]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[6]);
                break;
            case R.id.soccer:
                intent.putExtra(RSSmain.KEY_TITLE, titles[7]);
                intent.putExtra(RSSmain.KEY_URL, urls[7]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[7]);
                break;
            case R.id.bollywood:
                intent.putExtra(RSSmain.KEY_TITLE, titles[8]);
                intent.putExtra(RSSmain.KEY_URL, urls[8]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[8]);
                break;
            case R.id.hollywood:
                intent.putExtra(RSSmain.KEY_TITLE, titles[9]);
                intent.putExtra(RSSmain.KEY_URL, urls[9]);
                intent.putExtra(RSSmain.KEY_LOADING, loadingTexts[9]);
                break;
            default:
                throw new IllegalArgumentException();
        }
        startActivity(intent);
    }
}
