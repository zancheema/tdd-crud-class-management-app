package com.example.tddclassmanagementapp.data.source.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;

@Dao
public interface ClassRoomDao {
    @Insert
    void insert(ClassRoom c);

    @Query("SELECT * FROM class_rooms WHERE id = :id")
    ClassRoom get(String id);

    @Update
    void update(ClassRoom c);

    @Delete
    void delete(ClassRoom c);
}
