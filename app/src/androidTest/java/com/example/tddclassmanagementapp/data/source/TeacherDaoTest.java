package com.example.tddclassmanagementapp.data.source;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.example.tddclassmanagementapp.data.source.daos.TeacherDao;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

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
public class TeacherDaoTest {
    private AppDatabase testDb;
    private TeacherDao teacherDao;

    /**
     * Executes before each Test.
     * Initializes a new in-memory temporary database
     * and caches the Teacher database-access-object.
     */
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        testDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        teacherDao = testDb.getTeacherDao();
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
    public void insertNewTeacher_fetchTeacherById_returnsInsertedTeacher() {
        // Given: a teacher stored in database
        final Teacher mathsTeacher = new Teacher("Jane Doe");
        teacherDao.insert(mathsTeacher);

        // When: fetched by id
        Teacher fetchedTeacher = teacherDao.get(mathsTeacher.getId());

        // Then: returns the inserted teacher
        assertThat(fetchedTeacher, is(notNullValue()));
        assertThat(fetchedTeacher.getId(), is(mathsTeacher.getId()));
        assertThat(fetchedTeacher.getName(), is(mathsTeacher.getName()));
    }

    @Test
    public void updateTeacherName_fetchById_returnsUpdatedTeacher() {
        // Given: a teacher inserted in DB
        final Teacher teacher = new Teacher("John Doe");
        teacherDao.insert(teacher);

        // When: updated and fetched
        teacher.setName("Jane Doe");
        teacherDao.update(teacher);

        Teacher fetchedTeacher = teacherDao.get(teacher.getId());

        // Then: returns the updated teacher
        assertThat(fetchedTeacher, is(notNullValue()));
        assertThat(fetchedTeacher.getId(), is(teacher.getId()));
        assertThat(fetchedTeacher.getName(), is(teacher.getName()));
    }

    @Test
    public void deleteTeacherFromDb_fetchById_returnsNull() {
        // Given: a teacher inserted in DB
        final Teacher teacher = new Teacher("Jane Doe");
        teacherDao.insert(teacher);

        // When: deleted and fetched
         teacherDao.delete(teacher);
        Teacher fetchedTeacher = teacherDao.get(teacher.getId());

        // Then: the teacher is deleted and nothing is fetched
        assertThat(fetchedTeacher, is(nullValue()));
    }
}
