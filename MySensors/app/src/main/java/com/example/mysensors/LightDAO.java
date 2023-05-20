package com.example.mysensors;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface LightDAO {
    @Query("select * from Light")
    List<Light> getAllLight();

    @Insert
    void addto(Light data);

    @Update
    void updateto(Light data);

    @Delete
    void deleteto(Light data);
}
