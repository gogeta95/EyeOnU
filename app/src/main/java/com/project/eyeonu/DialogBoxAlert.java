package com.project.eyeonu;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Toast;

public class DialogBoxAlert extends AppCompatActivity {
    static Camera camera = null;
    MediaPlayer mp;
    Runnable b;
    Parameters p;
    Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_main);
        WebView wb = (WebView) findViewById(R.id.webView);
        wb.loadUrl("file:///android_asset/siren.gif");
        mp = MediaPlayer.create(getApplicationContext(), R.raw.song1);
        b = new Runnable() {
            @Override
            public void run() {
                try {
                    for (; ; ) {
                        flashOn();
                        Thread.sleep(200);
                        flashOff();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t = new Thread(b);
        PackageManager pm = getApplicationContext().getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(getApplicationContext(), "Your device doesn't have Flash!", Toast.LENGTH_SHORT).show();
        }

    }

    public void turnOFF(View v) {
        Toast.makeText(getApplicationContext(), "Alert Cancelled", Toast.LENGTH_LONG).show();
        mp.pause();
        mp.stop();
        mp.release();
        mp = null;
        stopThread();
        finish();
        System.exit(0);
    }

    @Override
    protected void onResume() {
        if (!t.isAlive())
            t.start();
        mp.start();
        mp.setLooping(true);
        super.onResume();
    }

    public void flashOff() {
        p.setFlashMode(Parameters.FLASH_MODE_OFF);
        camera.setParameters(p);
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public void flashOn() {
        camera = Camera.open();
        p = camera.getParameters();
        p.setFlashMode(Parameters.FLASH_MODE_TORCH);
        camera.setParameters(p);
        camera.startPreview();
    }

    @SuppressWarnings("deprecation")
    public void stopThread() {
        try {
            t.stop();
        } catch (Exception e) {

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dialog_box1, menu);
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
