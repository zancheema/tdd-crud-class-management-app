package com.example.tddclassmanagementapp.data.source;

import androidx.lifecycle.LiveData;

import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.data.source.entities.Student;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

import java.util.List;

public interface AppRepository {
    LiveData<List<Student>> observeAllStudents();

    LiveData<List<Student>> observeStudentsByClassId(String id);

    LiveData<Student> observeStudentById(String id);

    LiveData<List<Teacher>> observeAllTeachers();

    LiveData<Teacher> observeTeacherById(String id);

    LiveData<List<ClassRoom>> observeAllClassRooms();

    LiveData<List<ClassRoom>> observeClassRoomsByTeacherId(String id);

    LiveData<ClassRoom> observeClassRoomById(String id);

    void createStudent(Student s);

    void createTeacher(Teacher t);

    void createClassRoom(ClassRoom c);

    void updateStudent(Student s);

    void updateTeacher(Teacher t);

    void updateClassRoom(ClassRoom c);

    void deleteStudent(Student s);

    void deleteTeacher(Teacher t);

    void deleteClassRoom(ClassRoom c);
}
