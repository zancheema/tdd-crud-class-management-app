package com.example.tddclassmanagementapp.data.source.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tddclassmanagementapp.data.source.entities.Teacher;

@Dao
public interface TeacherDao {
    @Insert
    void insert(Teacher t);

    @Query("SELECT * FROM teachers WHERE id = :id")
    Teacher get(String id);

    @Update
    void update(Teacher t);

    @Delete
    void delete(Teacher t);
}
