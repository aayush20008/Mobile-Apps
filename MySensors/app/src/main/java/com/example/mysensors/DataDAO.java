package com.example.mysensors;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDAO {
    @Query("select * from proximity")
    List<Data> getAll();

    @Insert
    void addto(Data data);

    @Update
    void updateto(Data data);

    @Delete
    void deleteto(Data data);
}

