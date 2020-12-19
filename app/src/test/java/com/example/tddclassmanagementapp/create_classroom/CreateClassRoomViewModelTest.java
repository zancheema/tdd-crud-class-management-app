package com.example.tddclassmanagementapp.create_classroom;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.FakeTestRepository;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateClassRoomViewModelTest {
    private FakeTestRepository repository;
    private CreateClassRoomViewModel viewModel;

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
        viewModel = new CreateClassRoomViewModel(repository);
    }

    @Test
    public void createClassRoom_createsClassRoomAndGeneratesCreationCompleteEvent() {
        viewModel.setName("Maths");

        viewModel.createClassRoom();

        List<ClassRoom> classRooms = repository.observeAllClassRooms().getValue();
        assertThat(classRooms.get(0).getName(), is("Maths"));
        Event<Boolean> event = viewModel.observeCreationCompleteEvent().getValue();
        assertThat(event.getContentIfNotHandled(), is(true));
    }
}
