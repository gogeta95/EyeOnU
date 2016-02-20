package com.project.eyeonu;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class FavContacts extends Fragment {
    static int j, k = 0;
    static String[] numbers = new String[5];
    static String[] names = new String[5];
    SQLiteDatabase db;
    DataBaseContacts dbc;
    View v;
    Button b1, b2;
    ImageButton[] ib = new ImageButton[5];
    ListView lv;
    Cursor c;
    TextView favcon;
    ArrayAdapter<String> mAdapter1;
    String cNumber, name;
    int wid, hei;
    ArrayList<String> list1 = new ArrayList<String>();
    Integer[] imgbuttons = {R.id.imageButton1, R.id.imageButton2, R.id.imageButton3, R.id.imageButton4, R.id.imageButton5};

    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.favcontacts, container, false);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        wid = display.getWidth();
        hei = display.getHeight();

        Navdrawer.count = 0;

        b1 = (Button) v.findViewById(R.id.button);
        b2 = (Button) v.findViewById(R.id.button1);
        for (int i = 0; i < 5; i++) {
            ib[i] = (ImageButton) v.findViewById(imgbuttons[i]);
            ib[i].setVisibility(View.INVISIBLE);
        }
        favcon = (TextView) v.findViewById(R.id.favcon);
        favcon.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        lv = (ListView) v.findViewById(R.id.listView1);
        mAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.myitem, list1);
        lv.setAdapter(mAdapter1);

        wid = (int) (wid - (wid * 0.146));
        lv.getLayoutParams().width = wid;

        lv.requestLayout();
        mAdapter1.notifyDataSetChanged();
        dbc = new DataBaseContacts(getActivity());
        db = dbc.getWritableDatabase();
        names = dbc.getnames();
        numbers = dbc.getnumbers();
        if (names[0] == null) {
            j = 0;
            b2.setVisibility(View.INVISIBLE);
            favcon.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.VISIBLE);
        } else {
            for (k = 0; k < names.length; k++) {
                if (names[k] != null) {
                    list1.add(names[k] + " :  " + numbers[k]);
//    		YoYo.with(Techniques.SlideInLeft).duration(700).playOn(lv.getChildAt(k));
                    ib[k].setVisibility(View.VISIBLE);
                }
            }
            j = list1.size();
            if (list1.size() == 5) {
                b1.setVisibility(View.INVISIBLE);
                favcon.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
            }
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (j < 5) {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                    startActivityForResult(intent, 1);
                } else {
                    YoYo.with(Techniques.Tada).duration(700).playOn(b1);
                    Toast.makeText(getActivity().getApplicationContext(), "Cannot add more than 5 contacts!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
                ad.setCancelable(false);
                ad.setTitle("Favourites Contacts");
                ad.setMessage("Do you really want to remove all contacts from favourites?");
                ad.setButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dbc.clearDatabase();
                        list1.clear();
                        mAdapter1.notifyDataSetChanged();
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.INVISIBLE);
                        favcon.setVisibility(View.INVISIBLE);
                        for (int i = 0; i < 5; i++)
                            ib[i].setVisibility(View.INVISIBLE);
                        j = 0;
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
            }
        });
        ib[0].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                deleteAlert(0);
            }
        });
        ib[1].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                deleteAlert(1);
            }
        });
        ib[2].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                deleteAlert(2);
            }
        });
        ib[3].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                deleteAlert(3);
            }
        });
        ib[4].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                deleteAlert(4);
            }
        });
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int pos,
                                    long arg3) {
                names = dbc.getnames();
                numbers = dbc.getnumbers();
                AlertDialog fav = new AlertDialog.Builder(getActivity()).create();
                fav.setTitle("Favourite Contact");
                fav.setMessage("Do you want to : ");
                fav.setButton2("Call " + names[pos], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_CALL);
                        Toast.makeText(getActivity(), "Calling " + names[pos], Toast.LENGTH_SHORT).show();
                        i.setData(Uri.parse("tel:" + numbers[pos]));
                        startActivity(i);
                    }
                });
                fav.setButton("SMS " + names[pos], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("smsto:" + numbers[pos]);
                        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                        PackageManager pm = getActivity().getPackageManager();
                        //returns a list of installed application on your cellphone
                        List<ApplicationInfo> packages = pm.getInstalledApplications(0);
                        ;
                        boolean googlepack = false;
                        for (ApplicationInfo packageInfo : packages) {
                            if (packageInfo.packageName.equals("com.google.android.apps.messaging"))
                                googlepack = true;
                        }
                        if (googlepack == true)
                            i.setPackage("com.google.android.apps.messaging");
                        else
                            i.setPackage("com.android.mms");
                        Toast.makeText(getActivity(), "Messaging " + names[pos], Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                });
                fav.show();
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (data != null) {
                Uri contactdata = data.getData();
                if (contactdata != null) {
                    c = null;
                    try {
                        c = getActivity().getContentResolver().query(contactdata, null, null, null, null);
                        //moves the cursor to the first row
                        if (c != null && c.moveToFirst()) {
                            cNumber = c.getString(c.getColumnIndex("data1"));
                            name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        }
                    } finally {
                        if ((c != null) && (!c.isClosed()))
                            c.close();
                    }
                }
            }
            list1.add(name + " :  " + cNumber);
            ib[j].setVisibility(View.VISIBLE);
//        YoYo.with(Techniques.SlideInLeft).duration(700).playOn(item);
            mAdapter1.notifyDataSetChanged();
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            PhoneNumber phn = new PhoneNumber();
            try {
                phn = phoneUtil.parse(cNumber, "IN");
                cNumber = phoneUtil.format(phn, PhoneNumberFormat.E164);
//			Real Device Supports E164
//			numbersnew[i]=phoneUtil.format(phns[i], PhoneNumberFormat.NATIONAL);
//			National For Virtual Device
            } catch (NumberParseException e) {
                e.printStackTrace();
            }
            numbers[j] = cNumber;
            names[j] = name;
            addcontacts(j);
            j++;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addcontacts(int a) {
        long i = dbc.insertContacts(names[a], numbers[a]);
        if (i > 0)
            Toast.makeText(getActivity().getApplicationContext(), "Contact Added", Toast.LENGTH_SHORT).show();
        b2.setVisibility(View.VISIBLE);
    }

    public void checkVisibility() {
        int size = list1.size();
        for (int i = 0; i < 5; i++)
            ib[i].setVisibility(View.INVISIBLE);
        for (int i = 0; i < size; i++)
            ib[i].setVisibility(View.VISIBLE);
        if (size == 0)
            b2.setVisibility(View.INVISIBLE);
        if (size < 5) {
            favcon.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.VISIBLE);
        }
    }

    @SuppressWarnings("deprecation")
    public void deleteAlert(final int a) {
        AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
        ad.setCancelable(false);
        ad.setTitle("Remove Contact");
        ad.setMessage("Do you want to remove " + names[a] + " from favourites?");
        ad.setButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dbc.deletecontact(numbers[a]);
                dialog.dismiss();
                list1.remove(a);
                checkVisibility();
                mAdapter1.notifyDataSetChanged();
//		       		YoYo.with(Techniques.SlideOutRight).duration(700).playOn(item);
                j--;
                names = dbc.getnames();
                numbers = dbc.getnumbers();
            }
        });
        ad.setButton2("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        ad.show();
    }
}