package com.example.admin.finalnotetakingandroid;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 2016-12-21.
 */
public class DisplayData extends AppCompatActivity {

    Button btnDelete;
    ImageView imageDetail;
EditText title,body;
    DataBaseHandler db;
    Bitmap theImage;
    Button update;
    int imageId;
    String name,bodydetails,addddd;
    String getname,getbody;
    TextView iadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        imageDetail = (ImageView) findViewById(R.id.img);
        title = (EditText) findViewById(R.id.txtTitle);
        body = (EditText) findViewById(R.id.body);
        iadd = (TextView)findViewById(R.id.iadd);
        update =(Button)findViewById(R.id.update);
        /**
         * getting intent data from search and previous screen
         */
        Intent intnt = getIntent();
        theImage = (Bitmap) intnt.getParcelableExtra("imageicon");
       // theImage = (Bitmap) intnt.getParcelableExtra("imageicon");
        name = intnt.getStringExtra("imagename");
        title.setText(name);

        addddd = intnt.getStringExtra("address");
        iadd.setText("Image Location"+addddd);

        bodydetails = intnt.getStringExtra("body");
        body.setText(bodydetails);
        imageId = intnt.getIntExtra("imageid", 20);
        Log.d("Image ID:****", String.valueOf(imageId));
        imageDetail.setImageBitmap(theImage);
        Log.d("Image:::", String.valueOf(theImage));
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DataBaseHandler db = new DataBaseHandler(
                        DisplayData.this);
                /**
                 * Deleting records from database
                 */
                Log.d("Delete Image: ", "Deleting.....");
                db.deleteContact(new NoteModel(imageId));
                // /after deleting data go to main page
                Intent i = new Intent(DisplayData.this,
                        MainActivity.class);
                startActivity(i);
                finish();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHandler db = new DataBaseHandler(
                        DisplayData.this);
                getbody =body.getText().toString();
                getname = title.getText().toString();
                db.UpDate(new NoteModel(getname,getbody,imageDetail));
                Intent i = new Intent(DisplayData.this,
                        MainActivity.class);
                startActivity(i);
            }
        });
    }



}
