package com.example.tddclassmanagementapp.data.source.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tddclassmanagementapp.data.source.entities.Student;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student s);

    @Query("SELECT * FROM students WHERE roll_no = :roll")
    Student get(int roll);

    @Update
    void update(Student s);

    @Delete
    void delete(Student s);
}
