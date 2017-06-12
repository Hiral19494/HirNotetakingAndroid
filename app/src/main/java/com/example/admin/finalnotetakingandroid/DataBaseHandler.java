package com.example.admin.finalnotetakingandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016-12-20.
 */
public class DataBaseHandler  extends SQLiteOpenHelper
{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = " note_data_db";

    // Contacts table name
    private static final String TABLE_CONTACTS = " note_table_data";

    // Contacts Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_BODY = "body";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_DATE = "date";
    public  static  final String KEY_LAT = "latitude";
    public  static  final String KEY_LON = "longitude";
    public  static  final String KEY_ADD = "address";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"  + KEY_DATE + " TEXT,"
                + KEY_BODY + " TEXT,"  + KEY_LAT + " TEXT,"  + KEY_LON + " TEXT,"  + KEY_ADD + " TEXT,"
                + KEY_IMAGE + " BLOB" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read) Operations
     */

    public// Adding new contact
    void addContact(NoteModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact._name);
        values.put(KEY_DATE, contact._date);
        values.put(KEY_BODY, contact._body);
        values.put(KEY_IMAGE, contact._image);
        values.put(KEY_LAT,contact.lat);
        values.put(KEY_LON,contact.lon);
        values.put(KEY_ADD,contact.address);

        // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

     public boolean UpDate(NoteModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact._name);
        values.put(KEY_DATE, contact._date);
        values.put(KEY_BODY, contact._body);
        values.put(KEY_IMAGE, contact._image);
        values.put(KEY_LAT,contact.lat);
        values.put(KEY_LON,contact.lon);
        values.put(KEY_ADD,contact.address);

        // Contact Phone

        // Inserting Row
     return    db.update(TABLE_CONTACTS, values, KEY_ID + "=" + contact._id, null) > 0;
        //db.close(); // Closing database connection
    }
    // Getting single contact
    Cursor getContact(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME,KEY_DATE,KEY_BODY, KEY_IMAGE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        //NoteModel contact = new NoteModel(Integer.parseInt(cursor.getString(0)),
          //      cursor.getString(1), cursor.getBlob(2),cursor.getString(3),cursor.getString(4));

        // return contact
        return cursor;

    }
    public void deleteContact(NoteModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
        db.close();
    }

    // Getting All Contacts
    public List<NoteModel> getAllContacts() {
        List<NoteModel> contactList = new ArrayList<NoteModel>();
        // Select All Query
        String selectQuery =  "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NoteModel contact = new NoteModel();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.set_date(cursor.getString(2));
                contact.set_body(cursor.getString(3));
                contact.setLat(cursor.getDouble(4));
                contact.setLon(cursor.getDouble(5));
                contact.setAddress(cursor.getString(6));
                contact.setImage(cursor.getBlob(7));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
//        Log.d("image valueee", String.valueOf(cursor.getBlob(4)));
        Log.d("value", String.valueOf(contactList));
        return contactList;
    }
}

