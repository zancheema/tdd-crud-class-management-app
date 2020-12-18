package com.example.tddclassmanagementapp.data.source;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.example.tddclassmanagementapp.data.source.daos.ClassRoomDao;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ClassRoomDaoTest {
    private AppDatabase testDb;
    private ClassRoomDao classRoomDao;

    /**
     * Executes before each Test.
     * Initializes a new in-memory temporary database
     * and caches the ClassRoom database-access-object.
     */
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        testDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        classRoomDao = testDb.getClassRoomDao();
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
    public void getAll_returnsAllClassRooms() {
        // Given: list of classrooms
        List<ClassRoom> classRooms = List.of(
                new ClassRoom("CS Class", null),
                new ClassRoom("History Class", null),
                new ClassRoom("Maths Class", null)
        );
        for (ClassRoom c : classRooms) classRoomDao.insert(c);

        // When: fetched all
        List<ClassRoom> fetchClassRooms = classRoomDao.getAll();

        // Then: returns all classrooms
        assertThat(fetchClassRooms, is(classRooms));
    }

    @Test
    public void insertNewClass_fetchById_returnsInsertClass() {
        // Given: a ClassRoom
        ClassRoom classRoom = new ClassRoom("Physics Class", null);
        classRoomDao.insert(classRoom);

        // When: classRoom is fetched by id of inserted classRoom
        ClassRoom fetchedClassRoom = classRoomDao.getById(classRoom.getId());

        // Then: inserted classRoom is returned
        assertThat(fetchedClassRoom, is(notNullValue()));
        assertThat(fetchedClassRoom.getId(), is(classRoom.getId()));
        assertThat(fetchedClassRoom.getName(), is(classRoom.getName()));
        assertThat(fetchedClassRoom.getTeacherId(), is(classRoom.getTeacherId()));
    }

    @Test
    public void updateClassRoom_fetchById_returnsUpdatedClassRoom() {
        // Given: a ClassRoom
        ClassRoom classRoom = new ClassRoom("Physics Class", null);
        classRoomDao.insert(classRoom);

        // When: update classRoom and fetch it
        classRoom.setName("Maths class");
        classRoomDao.update(classRoom);
        ClassRoom updatedClassRoom = classRoomDao.getById(classRoom.getId());

        // Then: updated classRoom is returned
        assertThat(updatedClassRoom, is(notNullValue()));
        assertThat(updatedClassRoom.getId(), is(classRoom.getId()));
        assertThat(updatedClassRoom.getName(), is(classRoom.getName()));
        assertThat(updatedClassRoom.getTeacherId(), is(classRoom.getTeacherId()));
    }

    @Test
    public void deleteClassRoomFromDb_fetchById_returnsNull() {
        // Given: a ClassRoom
        ClassRoom classRoom = new ClassRoom("Physics Class", null);
        classRoomDao.insert(classRoom);

        // When: delete and fetch it by id
        classRoomDao.delete(classRoom);
        ClassRoom fetchedClassRoom = classRoomDao.getById(classRoom.getId());

        // Then: nothing is returned
        assertThat(fetchedClassRoom, is(nullValue()));
    }
}
