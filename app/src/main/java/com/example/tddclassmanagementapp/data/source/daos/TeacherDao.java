package com.example.tddclassmanagementapp.data.source.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tddclassmanagementapp.data.source.entities.Teacher;

import java.util.List;

@Dao
public interface TeacherDao {
    @Query("SELECT * FROM teachers ORDER BY name")
    List<Teacher> getAll();

    @Query("SELECT * FROM teachers WHERE id = :id")
    Teacher getById(String id);

    @Query("SELECT * FROM teachers WHERE id = :id")
    LiveData<Teacher> observeById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Teacher t);

    @Update
    void update(Teacher t);

    @Delete
    void delete(Teacher t);
}
