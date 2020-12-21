package com.example.tddclassmanagementapp.data.source.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;

import java.util.List;

@Dao
public interface ClassRoomDao {
    @Query("SELECT * FROM class_rooms ORDER BY name")
    List<ClassRoom> getAll();

    @Query("SELECT * FROM class_rooms WHERE id = :id")
    ClassRoom getById(String id);

    @Query("SELECT * FROM class_rooms WHERE teacher_id = :teacherId")
    List<ClassRoom> getByTeacherId(String teacherId);

    @Query("SELECT * FROM class_rooms WHERE teacher_id = :teacherId")
    LiveData<List<ClassRoom>> observeByTeacherId(String teacherId);

    @Query("SELECT * FROM class_rooms WHERE id = :id")
    LiveData<ClassRoom> observeById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ClassRoom c);

    @Update
    void update(ClassRoom c);

    @Delete
    void delete(ClassRoom c);
}
