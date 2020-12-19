package com.example.tddclassmanagementapp.edit_classroom;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.FakeTestRepository;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.edit_student.EditStudentViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EditClassRoomViewModelTest {
    private FakeTestRepository repository;
    private ClassRoom classRoom;
    private EditClassRoomViewModel viewModel;

    /**
     * Runs all background tasks on Main Thread
     */
    @Rule
    public final InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    /**
     * - Executes before each Test
     * - Initializes viewModel
     */
    @Before
    public void init() {
        repository = new FakeTestRepository();
        classRoom = new ClassRoom("Maths Class", null);
        viewModel = new EditClassRoomViewModel(classRoom, repository);
    }

    @Test
    public void updateClassRoom_updatesClassRoomInRepoAndGeneratesClassRoomUpdatedEvent() {
        String updatedName = "Physics Class";
        viewModel.setName(updatedName);
        viewModel.updateClassRoom();

        List<ClassRoom> repoClassRooms = repository.observeAllClassRooms().getValue();
        assertThat(repoClassRooms.size(), is(1));
        assertThat(repoClassRooms.get(0).getName(), is(updatedName));
        Event<Boolean> event = viewModel.observeUpdateCompleteEvent().getValue();
        assertThat(event.getContentIfNotHandled(), is(true));
    }
}
