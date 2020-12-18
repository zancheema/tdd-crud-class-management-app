package com.example.tddclassmanagementapp.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.data.source.entities.Student;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class FakeAppRepository implements AppRepository {
    private final MutableLiveData<List<Student>> observableStudents =
            new MutableLiveData<>(emptyList());

    @Override
    public LiveData<List<Student>> observeAllStudents() {
        return observableStudents;
    }

    @Override
    public LiveData<List<Student>> observeStudentsByClassId(String id) {
        return null;
    }

    @Override
    public LiveData<Student> observeStudentById(String id) {
        return null;
    }

    @Override
    public LiveData<List<Teacher>> observeAllTeacher() {
        return null;
    }

    @Override
    public LiveData<Teacher> observeTeacherById(String id) {
        return null;
    }

    @Override
    public LiveData<List<ClassRoom>> observeAllClassRooms() {
        return null;
    }

    @Override
    public LiveData<ClassRoom> observeClassById(String id) {
        return null;
    }

    @Override
    public void createStudent(Student s) {
        List<Student> students = new ArrayList<>(observableStudents.getValue());
        students.add(s);
        observableStudents.setValue(students);
    }

    @Override
    public void createTeacher(Teacher t) {

    }

    @Override
    public void createClassRoom(ClassRoom c) {

    }

    @Override
    public void updateStudent(Student s) {

    }

    @Override
    public void updateTeacher(Teacher t) {

    }

    @Override
    public void updateClassRoom(ClassRoom c) {

    }

    @Override
    public void deleteStudent(Student s) {

    }

    @Override
    public void deleteTeacher(Teacher t) {

    }

    @Override
    public void deleteClassRoom(ClassRoom c) {

    }
}
