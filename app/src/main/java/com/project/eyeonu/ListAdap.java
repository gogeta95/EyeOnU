package com.project.eyeonu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListAdap extends ArrayAdapter<Vids> {

    private final Context context;
    ArrayList<Vids> vids = new ArrayList<Vids>();

    public ListAdap(Context context, ArrayList<Vids> vids) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.listadap, vids);
        this.context = context;
        this.vids = vids;
    }

    @Override
    public void add(Vids object) {
        // TODO Auto-generated method stub
        super.add(object);
    }

    @Override
    public View getView(int position, View View, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (View == null) {
//			LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View = inflater.inflate(R.layout.listadap, parent, false);
        }

        Vids vid = vids.get(position);
        TextView title = (TextView) View.findViewById(R.id.title);
        title.setText(vid.gettitle());

        TextView link = (TextView) View.findViewById(R.id.link);
        link.setText(vid.getlink());

        ImageView imagev = (ImageView) View.findViewById(R.id.imagev);
        imagev.setImageBitmap(vid.getImg());
        return View;

    }


    public Bitmap getBitmap(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            input.close();
            return bitmap;
        } catch (Exception ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

}
