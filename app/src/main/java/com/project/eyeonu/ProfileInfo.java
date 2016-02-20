package com.project.eyeonu;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class ProfileInfo extends AppCompatActivity {
    Button b1, b2;
    int count = 0;
    EditText ed1, ed2, ed3;
    Spinner spn;
    ProfileData helper;
    SQLiteDatabase db;
    String uname, uemail, uphn, cntry;
    TextView tvu, tve, tvp, tvc;
    String country[] = {"--Select a Country--", "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan",
            "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil",
            "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros",
            "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor",
            "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany",
            "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq",
            "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo",
            "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar",
            "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia",
            "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway",
            "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda",
            "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia",
            "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan",
            "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailan", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia",
            "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu",
            "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterinfo);


        getSupportActionBar().setTitle(Html.fromHtml("<font color='white'>Your Profile </font>"));
        b1 = (Button) findViewById(R.id.clearbutton);
        b2 = (Button) findViewById(R.id.donebutton);

        tvu = (TextView) findViewById(R.id.textViewname);
        tve = (TextView) findViewById(R.id.textViewemail);
        tvp = (TextView) findViewById(R.id.textViewphn);
        tvc = (TextView) findViewById(R.id.textViewcon);

        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        //FOR SPINNER

        spn = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(getApplicationContext(), R.layout.mycountry, country);
        spn.setAdapter(adapt);
        spn.setSelection(76);

        //FOR DATABASE
        helper = new ProfileData(this);
        db = helper.getWritableDatabase();


        //FOR BUTTON CLICK FUNCTION
        b1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                spn.setSelection(0);
            }
        });

        b2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                uname = ed1.getText().toString();
                uemail = ed2.getText().toString();
                uphn = ed3.getText().toString();
                cntry = spn.getSelectedItem().toString();
                if ((uname.length() < 2) || (uemail.length() < 2) || (uphn.length() < 2) || (spn.getSelectedItemId() == 0)) {
                    if (uname.length() < 2)
                        YoYo.with(Techniques.Tada).duration(700).playOn(tvu);
                    if (uemail.length() < 2)
                        YoYo.with(Techniques.Tada).duration(700).playOn(tve);
                    if (uphn.length() < 2)
                        YoYo.with(Techniques.Tada).duration(700).playOn(tvp);
                    if (spn.getSelectedItemId() == 0)
                        YoYo.with(Techniques.Tada).duration(700).playOn(tvc);
                    Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    if ((android.util.Patterns.EMAIL_ADDRESS.matcher(uemail).matches() == false) || (uphn.matches("[0-9]{10}") == false)) {
                        if (android.util.Patterns.EMAIL_ADDRESS.matcher(uemail).matches() == false) {
                            YoYo.with(Techniques.Tada).duration(700).playOn(tve);
                            Toast.makeText(getApplicationContext(), "Enter a valid Email Address!", Toast.LENGTH_SHORT).show();
                        }
                        if (uphn.matches("[0-9]{10}") == false) {
                            YoYo.with(Techniques.Tada).duration(700).playOn(tvp);
                            Toast.makeText(getApplicationContext(), "Enter a valid Mobile Number!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        helper.insertUser(uname, uemail, uphn, cntry);
                        Intent it = new Intent(getApplicationContext(), OptionsScreen.class);
                        //it.putExtra("email", uemail);
                        startActivity(it);
                    }
                }
            }
        });
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
}