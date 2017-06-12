package com.example.admin.finalnotetakingandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Admin on 2016-12-21.
 */
public class ListDisplayAdapter extends ArrayAdapter<NoteModel> {
    Context context;

 public    byte[] outImage;
     public Bitmap theImage2;
    int layoutResourceId;
    // BcardImage data[] = null;
    ArrayList<NoteModel> data=new ArrayList<NoteModel>();
    public ListDisplayAdapter(Context context, int layoutResourceId, ArrayList<NoteModel> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ImageHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.text1);
            holder.date1 = (TextView) row.findViewById(R.id.date_row);
            holder.i1 = (ImageView)row.findViewById(R.id.img1);
            holder.body = (TextView)row.findViewById(R.id.body);
            holder.lat=(TextView)row.findViewById(R.id.lat);
            holder.lon =(TextView)row.findViewById(R.id.lon);
            holder.add = (TextView)row.findViewById(R.id.add);

            row.setTag(holder);
        }
        else
        {
            holder = (ImageHolder)row.getTag();
        }

        NoteModel picture = data.get(position);
        holder.txtTitle.setText(picture._name);
        holder.date1.setText(picture._date);
        holder.body.setText(picture._body);
//        holder.lat.setText((int) picture.lat);
  //      holder.lon.setText((int) picture.lon);
        holder.add.setText(picture.address);


        //convert byte to bitmap take from contact class

        //


        byte[] outImage = data.get(position)._image;
        Log.d("value:", String.valueOf(outImage));
         ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeByteArray(outImage,0,outImage.length);
        holder.i1.setImageBitmap(theImage);
        Log.d("hiii", String.valueOf(theImage));

        Log.d("value of adapter", String.valueOf(theImage2));
        return row;

    }

    static class ImageHolder
    {
        TextView date1;
        TextView txtTitle;
        ImageView i1;
        TextView body;
        TextView lat,lon,add;
    }
}

