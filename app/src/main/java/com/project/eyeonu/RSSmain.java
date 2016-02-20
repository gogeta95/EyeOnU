package com.project.eyeonu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class RSSmain extends AppCompatActivity {
    public static final String KEY_URL = "url_key";
    public static final String KEY_LOADING = "loading";
    public static final String KEY_TITLE = "title";
    ListView lv;
    ProgressDialog dialog;
    ArrayList<Vids> vids = new ArrayList<Vids>();
    ListAdap adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss);
        //dialog = ProgressDialog.show(this,"Loading","Loading Today's News : INDIA");

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getIntent().getStringExtra(KEY_LOADING));
        dialog.show();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("News: " + getIntent().getStringExtra(KEY_TITLE));
        }

        lv = (ListView) findViewById(R.id.lv);
        Myrun r = new Myrun(getIntent().getStringExtra(KEY_URL));
        Thread t = new Thread(r);
        t.start();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    class Myrun implements Runnable {
        String url;
        ArrayList<Vids> naya;

        public Myrun(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub

            final IoHandler handler = new IoHandler(url);
            handler.processFeed();
            naya = handler.getVids();

            RSSmain.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        adap = new ListAdap(RSSmain.this, vids);
                        lv.setAdapter(adap);
                        lv.setOnItemClickListener(new OnItemClickListener() {

                            @Override
                            public void onItemClick(
                                    AdapterView<?> parent, View view,
                                    int position, long id) {
                                // TODO Auto-generated method stub
                                //Toast.makeText(RSSmain.this, "Item no : "+position, 5000).show();
                                Vids v = new Vids("", "", "", null);
                                Iterator<Vids> i = naya.iterator();
                                for (int j = 0; j <= position; j++) {
                                    v = (Vids) i.next();
                                }
                                Intent in = new Intent(RSSmain.this, Webv.class);
                                in.putExtra("link", v.getlink());
                                startActivity(in);

                            }
                        });


                        Iterator<Vids> i = naya.iterator();
                        while (i.hasNext()) {
                            Vids v = (Vids) i.next();
                            adap.add(new Vids(v.gettitle(), v.getdescription(), v.getlink(), v.getImg()));
                            adap.notifyDataSetChanged();
                        }


                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        }

    }
}

