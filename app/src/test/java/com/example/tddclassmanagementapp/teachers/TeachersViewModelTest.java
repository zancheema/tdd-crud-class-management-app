package com.example.tddclassmanagementapp.teachers;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.FakeTestRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TeachersViewModelTest {
    private FakeTestRepository repository;
    private TeachersViewModel viewModel;

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
        viewModel = new TeachersViewModel(repository);
    }

    @Test
    public void addTeacher_generatesAddTeacherEvent() {
        viewModel.addTeacher();

        Event<Boolean> event = viewModel.observeAddTeacherEvent().getValue();
        assertThat(event.getContentIfNotHandled(), is(true));
    }
}
