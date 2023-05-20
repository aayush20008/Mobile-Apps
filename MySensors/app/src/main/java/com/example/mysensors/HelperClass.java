package com.example.mysensors;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Data.class,Light.class,GeoMag.class}, exportSchema = false, version = 1)
public abstract class HelperClass extends RoomDatabase {
    private static final String DB_NAME = "datadb";
    private static  HelperClass instance;

    public static synchronized HelperClass getDB(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, HelperClass.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract DataDAO dataDAO();
    public abstract LightDAO lightDAO();
    public abstract GeoDAO geoDAO();
}
