package com.project.eyeonu;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import java.util.List;

public class MyService extends Service {
    public static boolean service_status;
    public static String addressnew = "Address Not Available";
    public static double lat = 0.0000;
    public static double longi = 0.0000;
    AudioManager am;
    SQLiteDatabase db;
    DataBaseContacts dbc;
    SmsMessage[] smsMessages;
    String org;
    String body;
    String[] numbers = new String[5];
    String[] names = new String[5];
    BroadcastReceiver brnew;
    String locate = "Locate";
    String chmod = "SetRinger";
    String alert = "Alert";
    LocationManager lm;
    LocationListener locLis;
    //	String[] numbersnew=new String[5];
    PhoneNumber[] phns = new PhoneNumber[5];

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        getApplicationContext();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locLis = new LocationListener() {
            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
                switch (arg1) {
                    case LocationProvider.AVAILABLE:
                        Toast.makeText(getApplicationContext(), "AVAILABLE", Toast.LENGTH_SHORT).show();
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        Toast.makeText(getApplicationContext(), "OUT_OF_SERVICE", Toast.LENGTH_SHORT).show();
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        Toast.makeText(getApplicationContext(), "TEMPORARILY_UNAVAILABLE", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getApplicationContext(), provider + " Enabled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(getApplicationContext(), provider + " Disabled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLocationChanged(Location loc) {
                addressnew = "";
                lat = loc.getLatitude();
                longi = loc.getLongitude();
                Geocoder gc = new Geocoder(getApplicationContext());
                List<Address> address;
                try {
                    address = gc.getFromLocation(lat, longi, 1);
                    for (int i = 0; i < address.size(); i++) {
                        Address a = address.get(i);
                        //Returns the largest index currently in use to specify an address line.
                        for (int j = 0; j < a.getMaxAddressLineIndex(); j++) {

                            addressnew += a.getAddressLine(j) + "\n";
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Address Retreived", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dbc = new DataBaseContacts(getApplicationContext());
        db = dbc.getWritableDatabase();
        names = dbc.getnames();
        numbers = dbc.getnumbers();
        getApplicationContext();
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        brnew = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle b = intent.getExtras();
                if (b != null) {
                    Object[] pdus = (Object[]) b.get("pdus");
                    smsMessages = new SmsMessage[pdus.length];
                    for (int j = 0; j < pdus.length; j++) {
                        //Create an SmsMessage from a raw PDU with the specified message format.
                        smsMessages[j] = SmsMessage.createFromPdu((byte[]) pdus[j]);
                        if (j == 0) {
                            //Returns the originating address (sender) of this SMS message in String form or null if unavailable
                            org = smsMessages[j].getOriginatingAddress();
                            //Returns the message body as a String, if it exists and is text based.
                            body = smsMessages[j].getMessageBody();
                            for (int k = 0; k < numbers.length; k++) {
                                if (org.equals(numbers[k])) {
                                    if (body.equals(chmod)) {
                                        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                                    }
                                    if (body.equals(locate)) {
                                        SmsManager sms = SmsManager.getDefault();
                                        sms.sendTextMessage(org, null, addressnew, null, null);
                                    }
                                    if (body.equals(alert)) {
                                        Intent newi = new Intent(getApplicationContext(), DialogBoxAlert.class);
                                        newi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(newi);
                                    }
                                }
                            }
                        }
                    }

                }
            }
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 5, locLis);
        registerReceiver(brnew, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        service_status = true;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        lm.removeUpdates(locLis);
        unregisterReceiver(brnew);
        service_status = false;
        super.onDestroy();
    }
}