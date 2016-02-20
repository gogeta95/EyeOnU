package com.project.eyeonu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class LetsTrack extends Fragment {
    ImageView iv;
    View v;
    TextView startstop, tv1, tv2, tv3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.letstrack, container, false);
        Navdrawer.count = 0;
        iv = (ImageView) v.findViewById(R.id.imageView1);
        startstop = (TextView) v.findViewById(R.id.textViewtrack);
        tv1 = (TextView) v.findViewById(R.id.textView1);
        tv2 = (TextView) v.findViewById(R.id.textView2);
        tv3 = (TextView) v.findViewById(R.id.textViewhome);
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        if (MyService.service_status == true) {
            iv.setImageResource(R.drawable.on);
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
            startstop.setText("Click Button to Stop Service");
            YoYo.with(Techniques.SlideInLeft).duration(700).playOn(tv1);
            YoYo.with(Techniques.SlideInRight).duration(700).playOn(tv2);
            YoYo.with(Techniques.SlideInLeft).duration(700).playOn(tv3);
        }
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (MyService.service_status == false) {
                    iv.setImageResource(R.drawable.on);
                    getActivity().startService(new Intent(getActivity(), MyService.class));
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
                    tv3.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInLeft).duration(700).playOn(tv1);
                    YoYo.with(Techniques.SlideInRight).duration(700).playOn(tv2);
                    YoYo.with(Techniques.SlideInLeft).duration(700).playOn(tv3);
                    startstop.setText("Click Button to Stop Service");
                    Toast.makeText(getActivity(), "Service Started", Toast.LENGTH_SHORT).show();
                }
                if (MyService.service_status == true) {
                    iv.setImageResource(R.drawable.off);
                    getActivity().stopService(new Intent(getActivity(), MyService.class));
                    YoYo.with(Techniques.SlideOutLeft).duration(700).playOn(tv1);
                    YoYo.with(Techniques.SlideOutRight).duration(700).playOn(tv2);
                    YoYo.with(Techniques.SlideOutLeft).duration(700).playOn(tv3);
                    startstop.setText("Click Button to Start Service");
                    Toast.makeText(getActivity(), "Service Stopped", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return v;
    }
}
