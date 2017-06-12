package com.example.admin.finalnotetakingandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Admin on 2016-12-20.
 */
public class NoteAdapter  extends AppCompatActivity {
    EditText Title,body;
    public static String curDate = "";
    ImageView imageDetail;
    int imageId;
   public double latitude;
    public double longitude;
    String address = "";

    Button b1;
    private final int requestCode = 20;
    DataBaseHandler db;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_list);

db= new DataBaseHandler(this);
        imageDetail = (ImageView) findViewById(R.id.imgIcon);
        Title = (EditText)findViewById(R.id.txtTitle);
        body = (EditText)findViewById(R.id.body);
        b1= (Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);


                GPSService mGPSService = new GPSService(NoteAdapter.this);
                mGPSService.getLocation();

                if (mGPSService.isLocationAvailable == false) {

                    // Here you can ask the user to try again, using return; for that
                    Toast.makeText(NoteAdapter.this, "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();
                    return;

                    // Or you can continue without getting the location, remove the return; above and uncomment the line given below
                    // address = "Location not available";
                } else {

                    // Getting location co-ordinates
                     latitude = mGPSService.getLatitude();
                     longitude = mGPSService.getLongitude();
                    Toast.makeText(NoteAdapter.this, "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();

                    address = mGPSService.getLocationAddress();

                    //t1.setText("Latitude: " + latitude + " \nLongitude: " + longitude);
                    //t2.setText("Address: " + address);
                }

                Toast.makeText(NoteAdapter.this, "Your address is: " + address, Toast.LENGTH_SHORT).show();

                // make sure you close the gps after using it. Save user's battery power
                mGPSService.closeGPS();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
             bitmap = (Bitmap)data.getExtras().get("data");

           // String partFilename = currentDateFormat();
           // storeCameraPhotoInSDCard(bitmap, partFilename);

            // display the image from SD Card to ImageView Control
           // String storeFilename = "photo_" + partFilename + ".jpg";
            //Bitmap mBitmap = getImageFileFromSDCard(storeFilename);
            //  imageHolder.setImageBitmap(mBitmap);
            imageDetail.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.noteedit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                  bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte imageInByte[] = stream.toByteArray();
                Log.d("name",Title.getText().toString());
                Log.d("image", String.valueOf(imageInByte));
                Log.d("body",body.getText().toString());


                long msTime = System.currentTimeMillis();
                Date curDateTime = new Date(msTime);

                SimpleDateFormat formatter = new SimpleDateFormat("d'/'M'/'y");
                curDate = formatter.format(curDateTime);
                Log.d("date",curDate);
                db.addContact(new NoteModel(Title.getText().toString(),curDate,body.getText().toString(),latitude,longitude,address,imageInByte ));
                Log.d("success","success");
                Intent i = new Intent(NoteAdapter.this,
                        MainActivity.class);
                startActivity(i);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    }

