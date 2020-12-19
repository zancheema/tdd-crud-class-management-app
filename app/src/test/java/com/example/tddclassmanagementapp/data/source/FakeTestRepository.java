package com.example.tddclassmanagementapp.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.data.source.entities.Student;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class FakeTestRepository implements AppRepository {
    /* Maps a student by its rollNo, used to fetched student by rollNo */
    private final Map<Integer, Student> studentData = new HashMap<>();
    /* Maps a teacher by its id, used to fetched teacher by id */
    private final Map<String, Teacher> teacherData = new HashMap<>();
    /* Maps a classRoom by its id, used to fetched classRoom by id */
    private final Map<String, ClassRoom> classRoomData = new HashMap<>();

    private final MutableLiveData<List<Student>> observableStudents =
            new MutableLiveData<>(emptyList());
    private final MutableLiveData<List<Teacher>> observableTeachers =
            new MutableLiveData<>(emptyList());
    private MutableLiveData<List<ClassRoom>> observableClassRooms =
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
    public LiveData<List<Teacher>> observeAllTeachers() {
        return observableTeachers;
    }

    @Override
    public LiveData<Teacher> observeTeacherById(String id) {
        return null;
    }

    @Override
    public LiveData<List<ClassRoom>> observeAllClassRooms() {
        return observableClassRooms;
    }

    @Override
    public LiveData<ClassRoom> observeClassById(String id) {
        return null;
    }

    @Override
    public void createStudent(Student s) {
        studentData.put(s.getRollNo(), s);
        observableStudents.setValue(new ArrayList<>(studentData.values()));
    }

    @Override
    public void createTeacher(Teacher t) {
        teacherData.put(t.getId(), t);
        observableTeachers.setValue(new ArrayList<>(teacherData.values()));
    }

    @Override
    public void createClassRoom(ClassRoom c) {
        classRoomData.put(c.getId(), c);
        observableClassRooms.setValue(new ArrayList<>(classRoomData.values()));
    }

    @Override
    public void updateStudent(Student s) {
        createStudent(s);
    }

    @Override
    public void updateTeacher(Teacher t) {
        createTeacher(t);
    }

    @Override
    public void updateClassRoom(ClassRoom c) {
        createClassRoom(c);
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
