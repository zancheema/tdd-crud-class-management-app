package com.example.tddclassmanagementapp.data.source;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tddclassmanagementapp.data.source.daos.TeacherDao;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

@Database(entities = {Teacher.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TeacherDao getTeacherDao();
}
