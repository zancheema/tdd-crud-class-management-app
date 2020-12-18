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

import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
    public void getAll_returnsAllStudents() {
        // Given: a list of students
        List<Student> students = List.of(
                new Student(1, "John", null),
                new Student(2, "Jane", null),
                new Student(3, "Doe", null)
        );
        for (Student s : students) {
            studentDao.insert(s);
        }

        // When: fetched all
        List<Student> fetchedStudents = studentDao.getAll();

        // Then: returns all the inserted students
        assertThat(fetchedStudents, is(notNullValue()));
        assertThat(fetchedStudents, is(students));
    }

    @Test
    public void getByClassId_returnsStudentsBelongingToClass() {
        // Given: a list of students
        List<Student> students = List.of(
                new Student(1, "John", "class_1"),
                new Student(2, "Jane", "class_1"),
                new Student(3, "Doe", "class_2")
        );
        for (Student s : students) {
            studentDao.insert(s);
        }

        // When fetched by classId
        List<Student> fetchedStudents = studentDao.getByClassId("class_1");

        // Then: returns students belonging to the class
        assertThat(fetchedStudents, is(notNullValue()));
        assertThat(fetchedStudents, is(List.of(students.get(0), students.get(1))));
    }

    @Test
    public void insertStudent_fetchById_returnsInsertedStudent() {
        // Given: a Student
        final Student student = new Student(1, "Jane Doe", null);
        studentDao.insert(student);

        // When: fetched by id
        Student fetchedStudent = studentDao.getById(1);

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
        Student fetchedStudent = studentDao.getById(1);

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
        Student fetchedStudent = studentDao.getById(student.getRollNo());

        // Then: null is returned
        assertThat(fetchedStudent, is(nullValue()));
    }
}
