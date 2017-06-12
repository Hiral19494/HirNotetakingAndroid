package com.example.admin.finalnotetakingandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView dataList;
    ArrayList<NoteModel> imageArry = new ArrayList<NoteModel>();
    ListDisplayAdapter imageAdapter;
  public   DataBaseHandler db;
    String address;
    byte[] imageIcon;
    private static final int ACTIVITY_EDIT=1;
   public String imageName,imagebody;
    int imageId;
public     Bitmap theImage1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dataList = (ListView) findViewById(R.id.listview);
        db = new DataBaseHandler(this);
        List<NoteModel> contacts = db.getAllContacts();
        Log.d("hi","hiii");
        for (NoteModel cn : contacts) {
            String log = "ID:" + cn.getID() + " Name: " + cn.getName()
                    + " ,Image: " + cn.getImage()+"Date:"+ cn.get_date() + "Body:" + cn.get_body();

             //Writing Contacts to log
            Log.d("Result: ", log);
            // add contacts data in arrayList
            imageArry.add(cn);


     }
        /**
         * Set Data base Item into listview
         */
        imageAdapter = new ListDisplayAdapter(this, R.layout.screen_display,imageArry);
        dataList.setAdapter(imageAdapter);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageName = imageArry.get(position).getName();
                imageId = imageArry.get(position).getID();

                imagebody = imageArry.get(position).get_body();
                Log.d("Before Send:****", imageIcon + "-" + imageId);

                address = imageArry.get(position).getAddress();
                // convert byte to bitmap

                imageIcon = imageArry.get(position).getImage();
               ByteArrayInputStream imageStream = new ByteArrayInputStream(imageIcon);
                theImage1 = BitmapFactory.decodeStream(imageStream);


                Log.d("image value", String.valueOf(theImage1));
                Intent intent = new Intent(MainActivity.this,
                        DisplayData.class);
                intent.putExtra("imagename", imageName);
                intent.putExtra("imageid", imageId);
                intent.putExtra("imageicon",theImage1);
                intent.putExtra("body",imagebody);
                intent.putExtra("address",address);
                startActivity(intent);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notelist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:

                Intent i = new Intent(MainActivity.this,NoteAdapter.class);
                startActivity(i);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
