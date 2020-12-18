package com.example.tddclassmanagementapp.data.source.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tddclassmanagementapp.data.source.entities.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM students ORDER BY roll_no")
    List<Student> getAll();

    @Query("SELECT * FROM students WHERE class_id = :classId ORDER BY roll_no")
    List<Student> getByClassId(String classId);

    @Query("SELECT * FROM students WHERE roll_no = :roll")
    Student getById(int roll);

    @Query("SELECT * FROM students ORDER BY roll_no")
    LiveData<List<Student>> observeAll();

    @Query("SELECT * FROM students WHERE class_id = :classId ORDER BY roll_no")
    LiveData<List<Student>> observeByClassId(String classId);

    @Query("SELECT * FROM students WHERE roll_no = :roll")
    LiveData<Student> observeById(int roll);

    @Insert
    void insert(Student s);

    @Update
    void update(Student s);

    @Delete
    void delete(Student s);
}