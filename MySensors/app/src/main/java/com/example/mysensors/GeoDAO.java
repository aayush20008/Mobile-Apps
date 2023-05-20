package com.example.mysensors;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GeoDAO {
    @Query("select * from Geo")
    List<GeoMag> getAllMag();

    @Insert
    void addto( GeoMag geo);

    @Update
    void updateto(GeoMag data);

    @Delete
    void deleteto(GeoMag data);
}
