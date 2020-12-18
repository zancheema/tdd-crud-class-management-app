package com.example.tddclassmanagementapp.data.source;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tddclassmanagementapp.data.source.daos.ClassRoomDao;
import com.example.tddclassmanagementapp.data.source.daos.StudentDao;
import com.example.tddclassmanagementapp.data.source.daos.TeacherDao;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.data.source.entities.Student;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

@Database(
        entities = {Teacher.class, ClassRoom.class, Student.class},
        exportSchema = false,
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TeacherDao getTeacherDao();

    public abstract ClassRoomDao getClassRoomDao();

    public abstract StudentDao getStudentDao();
}
