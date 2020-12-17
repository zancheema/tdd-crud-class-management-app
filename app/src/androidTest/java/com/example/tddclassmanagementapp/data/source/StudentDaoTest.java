package com.example.tddclassmanagementapp.data.source;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.example.tddclassmanagementapp.data.source.daos.StudentDao;
import com.example.tddclassmanagementapp.data.source.entities.Student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class StudentDaoTest {
    private AppDatabase testDb;
    private StudentDao studentDao;

    /**
     * Executes before each Test.
     * Initializes a new in-memory temporary database
     * and caches the Student database-access-object.
     */
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        testDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        studentDao = testDb.getStudentDao();
    }

    /**
     * Executes after completion of each Test.
     * Closes the database, removes all inserted data and frees up memory
     */
    @After
    public void closeDb() {
        testDb.close();
    }

    @Test
    public void insertStudent_fetchById_returnsInsertedStudent() {
        // Given: a Student
        final Student student = new Student(1, "Jane Doe", null);
        studentDao.insert(student);

        // When: fetched by id
        Student fetchedStudent = studentDao.get(1);

        // Then: the inserted student is returned
        assertThat(fetchedStudent, is(notNullValue()));
        assertThat(fetchedStudent.getRollNo(), is(student.getRollNo()));
        assertThat(fetchedStudent.getName(), is(student.getName()));
        assertThat(fetchedStudent.getClassId(), is(student.getClassId()));
    }

    @Test
    public void updateStudent_fetchById_returnsUpdatedStudent() {
        // Given: a Student
        final Student student = new Student(1, "Jane Doe", null);
        studentDao.insert(student);

        // When: update and fetch by id
        student.setName("John Doe");
        studentDao.update(student);
        Student fetchedStudent = studentDao.get(1);

        // Then: the inserted student is returned
        assertThat(fetchedStudent, is(notNullValue()));
        assertThat(fetchedStudent.getRollNo(), is(student.getRollNo()));
        assertThat(fetchedStudent.getName(), is(student.getName()));
        assertThat(fetchedStudent.getClassId(), is(student.getClassId()));
    }

    @Test
    public void deleteStudentFromDb_fetchById_returnsNull() {
        // Given: a Student
        final Student student = new Student(1, "Jane Doe", null);
        studentDao.insert(student);

        // When: delete and fetch by id
        studentDao.delete(student);
        Student fetchedStudent = studentDao.get(student.getRollNo());

        // Then: null is returned
        assertThat(fetchedStudent, is(nullValue()));
    }
}
