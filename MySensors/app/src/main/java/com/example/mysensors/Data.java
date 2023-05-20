package com.example.mysensors;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "proximity")
public class Data {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Value")
    private String title;

    @ColumnInfo(name = "Remark")
    private String amount;

    Data(int id, String title, String amount){
        this.id =  id;
        this.amount = amount;
        this.title = title;
    }
    @Ignore
    Data(String title, String amount){
        this.amount = amount;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

@Entity(tableName = "Light")
class Light {
    @PrimaryKey(autoGenerate = true)
    private int l_id;
    @ColumnInfo(name = "Values")
    private float values;

    Light(int l_id,float values){
        this.l_id = l_id;
        this.values = values;
    }
    @Ignore
    Light(float values){
        this.values = values;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public float getValues() {
        return values;
    }

    public void setValues(float values) {
        this.values = values;
    }
}

@Entity(tableName = "Geo")
class GeoMag{
    @PrimaryKey(autoGenerate = true)
    private int g_id;
    @ColumnInfo(name = "Values")
    private float vals;

    GeoMag(int g_id, float vals){
        this.g_id = g_id;
        this.vals = vals;
    }
    @Ignore
    GeoMag(float vals){
        this.vals = vals;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public float getVals() {
        return vals;
    }

    public void setVals(float vals) {
        this.vals = vals;
    }
}
