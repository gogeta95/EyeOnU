package com.project.eyeonu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class HowItWorks extends Fragment {
    //TextView tvh,tvt,tvw,tvp1,tvp2,tvp3,tvp4;
    ScrollView s1, s2, s3, s4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.howitworks, container, false);

        Navdrawer.count = 0;

        s1 = (ScrollView) v.findViewById(R.id.scrollView1);
        s2 = (ScrollView) v.findViewById(R.id.scrollView2);
        s3 = (ScrollView) v.findViewById(R.id.scrollView3);
        s4 = (ScrollView) v.findViewById(R.id.scrollView4);

        YoYo.with(Techniques.SlideInLeft).duration(900).playOn(s1);
        YoYo.with(Techniques.SlideInRight).duration(900).playOn(s2);
        YoYo.with(Techniques.SlideInLeft).duration(900).playOn(s3);
        YoYo.with(Techniques.SlideInRight).duration(900).playOn(s4);

	
	/*tvh=(TextView) v.findViewById(R.id.textViewhome);
	tvt=(TextView) v.findViewById(R.id.textViewtrack);
	tvw=(TextView) v.findViewById(R.id.textViewwhere);
	tvp1=(TextView) v.findViewById(R.id.textViewph1);
	tvp2=(TextView) v.findViewById(R.id.textViewph2);
	tvp3=(TextView) v.findViewById(R.id.textViewph3);
	tvp4=(TextView) v.findViewById(R.id.textViewph4);
	YoYo.with(Techniques.BounceInLeft).duration(900).playOn(tvh);
	YoYo.with(Techniques.BounceInRight).duration(900).playOn(tvt);
	YoYo.with(Techniques.BounceInLeft).duration(900).playOn(tvw);
	YoYo.with(Techniques.BounceInRight).duration(900).playOn(tvp1);
	YoYo.with(Techniques.BounceInRight).duration(900).playOn(tvp2);
	YoYo.with(Techniques.BounceInRight).duration(900).playOn(tvp3);
	YoYo.with(Techniques.BounceInRight).duration(900).playOn(tvp4); */
        return v;
    }
}