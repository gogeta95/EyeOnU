package com.project.eyeonu;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SOS extends Fragment {
    View v;
    ImageButton sos;
    SQLiteDatabase db;
    DataBaseContacts dbc;
    String[] numbers = new String[5];
    String[] names = new String[5];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.sos, container, false);
        Navdrawer.count = 0;
        sos = (ImageButton) v.findViewById(R.id.imageButton1);
        dbc = new DataBaseContacts(getActivity());
        db = dbc.getWritableDatabase();
        names = dbc.getnames();
        numbers = dbc.getnumbers();
        sos.setOnClickListener(new OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View arg0) {
                if (names[0] == null) {
                    YoYo.with(Techniques.Tada).duration(700).playOn(sos);
                    Toast.makeText(getActivity(), "Add Favourites!", Toast.LENGTH_SHORT).show();
                } else {
                    if (MyService.service_status == true) {
                        AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
                        ad.setCancelable(false);
                        ad.setTitle("SOS Alert!!!");
                        ad.setMessage("Do you really want to send you location to your favourites?");
                        ad.setButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SmsManager sms = SmsManager.getDefault();
                                for (int i = 0; i < names.length; i++)
                                    sms.sendTextMessage(numbers[i], null, "Emergency!!!\nMy Current Location: " + MyService.addressnew, null, null);
                                Toast.makeText(getActivity(), "Location sent to favourites.", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        ad.setButton2("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                Toast.makeText(getActivity(), "Operation cancelled.", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        ad.show();
                    } else {
                        YoYo.with(Techniques.Tada).duration(700).playOn(sos);
                        Toast.makeText(getActivity(), "Start Service First!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
}
