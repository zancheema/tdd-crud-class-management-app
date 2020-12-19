package com.example.tddclassmanagementapp.create_teacher;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.FakeTestRepository;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateTeacherViewModelTest {
    private FakeTestRepository repository;
    private CreateTeacherViewModel viewModel;

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
        viewModel = new CreateTeacherViewModel(repository);
    }

    @Test
    public void createTeacher_nameIsNotEmpty_savesTeacherInRepositoryAndGeneratesCreationCompleteEvent() {
        viewModel.setName("Jane");

        viewModel.createTeacher();

        List<Teacher> teachers = repository.observeAllTeachers().getValue();
        assertThat(teachers.get(0).getName(), is("Jane"));
        Event<Boolean> event = viewModel.observeCreationCompleteEvent().getValue();
        assertThat(event.getContentIfNotHandled(), is(true));
    }
}
