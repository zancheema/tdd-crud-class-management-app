package com.example.tddclassmanagementapp.classrooms;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.FakeTestRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassRoomsViewModelTest {
    private FakeTestRepository repository;
    private ClassRoomsViewModel viewModel;

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
        viewModel = new ClassRoomsViewModel(repository);
    }

    @Test
    public void addClassRoom_generatesAddClassRoomEvent() {
        viewModel.addClassRoom();

        Event<Boolean> event = viewModel.observeAddClassRoomEvent().getValue();
        assertThat(event.getContentIfNotHandled(), is(true));
    }
}
