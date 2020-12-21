package com.example.tddclassmanagementapp.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.tddclassmanagementapp.data.source.daos.ClassRoomDao;
import com.example.tddclassmanagementapp.data.source.daos.StudentDao;
import com.example.tddclassmanagementapp.data.source.daos.TeacherDao;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.data.source.entities.Student;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import static java.util.Collections.emptyList;

public class DefaultAppRepository implements AppRepository {
    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private ClassRoomDao classRoomDao;
    private Executor executor;

    private final MutableLiveData<List<Student>> observableStudents =
            new MutableLiveData<>(emptyList());
    private final MutableLiveData<List<Teacher>> observableTeachers =
            new MutableLiveData<>(emptyList());
    private MutableLiveData<List<ClassRoom>> observableClassRooms =
            new MutableLiveData<>(emptyList());

    public DefaultAppRepository(StudentDao studentDao, TeacherDao teacherDao, ClassRoomDao classRoomDao, Executor executor) {
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.classRoomDao = classRoomDao;
        this.executor = executor;
    }

    @Override
    public LiveData<List<Student>> observeAllStudents() {
        refreshStudents();
        return observableStudents;
    }

    private void refreshStudents() {
        executor.execute(() -> {
            observableStudents.postValue(studentDao.getAll());
        });
    }

    @Override
    public LiveData<List<Student>> observeStudentsByClassId(String id) {
        return studentDao.observeByClassId(id);
    }

    @Override
    public LiveData<Student> observeStudentById(String id) {
        return null;
    }

    @Override
    public LiveData<List<Teacher>> observeAllTeachers() {
        refreshTeachers();
        return observableTeachers;
    }

    private void refreshTeachers() {
        executor.execute(() -> {
            observableTeachers.postValue(teacherDao.getAll());
        });
    }

    @Override
    public LiveData<Teacher> observeTeacherById(String id) {
        return teacherDao.observeById(id);
    }

    @Override
    public LiveData<List<ClassRoom>> observeAllClassRooms() {
        refreshClassRooms();
        return observableClassRooms;
    }

    @Override
    public LiveData<List<ClassRoom>> observeClassRoomsByTeacherId(String id) {
        return classRoomDao.observeByTeacherId(id);
    }

    public void refreshClassRooms() {
        executor.execute(() -> {
            observableClassRooms.postValue(classRoomDao.getAll());
        });
    }

    @Override
    public LiveData<ClassRoom> observeClassRoomById(String id) {
        return classRoomDao.observeById(id);
    }

    @Override
    public void createStudent(Student s) {
        // Validate primary key
        for (Student x : observableStudents.getValue()) {
            if (x.getRollNo() == s.getRollNo()) return;
        }
        executor.execute(() -> {
            studentDao.insert(s);
            refreshStudents();
        });
    }

    @Override
    public void createTeacher(Teacher t) {
        executor.execute(() -> {
            teacherDao.insert(t);
            refreshTeachers();
        });
    }

    @Override
    public void createClassRoom(ClassRoom c) {
        executor.execute(() -> {
            classRoomDao.insert(c);
            refreshClassRooms();
        });
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
        executor.execute(() -> {
            studentDao.delete(s);
            refreshStudents();
        });
    }

    @Override
    public void deleteTeacher(Teacher t) {
        executor.execute(() -> {
            teacherDao.delete(t);
            refreshTeachers();
        });
    }

    @Override
    public void deleteClassRoom(ClassRoom c) {
        executor.execute(() -> {
            classRoomDao.delete(c);
            refreshClassRooms();
        });
    }
}
