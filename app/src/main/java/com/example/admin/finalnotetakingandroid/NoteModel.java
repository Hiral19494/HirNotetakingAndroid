package com.example.admin.finalnotetakingandroid;

import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Admin on 2016-12-20.
 */
public class NoteModel {
    int _id;
    String _name;
    byte[] _image;
    String _body;
    String _date;

    public NoteModel(String name, String body, ImageView imageDetail) {
        this._name =name;
        this._body = String.valueOf(body);

    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    double lat ;

    public NoteModel(String _name, String _date,String _body, double lat,  double lon, String address, byte[] _image) {
        this._name = _name;
        this._body = _body;
        this._image = _image;
        this.lat = lat;
        this._date = _date;
        this.lon = lon;
        this.address = address;
    }

    double lon;
    String address;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    // Empty constructor

    public NoteModel(String _name,  String _body, String _date, double lat, double lon,byte[] _image) {
        this._name = _name;
        this._image = _image;
        this._body = _body;
        this._date = _date;
        this.lat = lat;
        this.lon = lon;
    }


    // constructor

    public NoteModel() {

    }

    public NoteModel(String _name,String _date,String _body, byte[] _image  ) {
        this._name = _name;
        this._body = _body;
        this._image = _image;
        this._date = _date;
    }

    public NoteModel(int _id, String _name, byte[] _image, String _body, String _date) {
        this._id = _id;
        this._name = _name;
        this._image = _image;
        this._body = _body;
        this._date = _date;

    }

    public NoteModel(int imageId) {
        this._id = imageId;
    }


    public String get_body() {
        return _body;
    }

    public void set_body(String _body) {
        this._body = _body;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int keyId) {
        this._id = keyId;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting phone number
    public byte[] getImage() {
        return this._image;
    }

    // setting phone number
    public void setImage(byte[] image) {
        this._image = image;
    }
}
