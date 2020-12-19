package com.example.tddclassmanagementapp.students;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.FakeTestRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentsViewModelTest {
    private FakeTestRepository repository;
    private StudentsViewModel viewModel;

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
        viewModel = new StudentsViewModel(repository);
    }

    @Test
    public void addStudent_generatesAddStudentEvent() {
        viewModel.addStudent();

        Event<Boolean> event = viewModel.observeAddStudentEvent().getValue();
        assertThat(event.getContentIfNotHandled(), is(true));
    }
}
