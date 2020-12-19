package com.example.tddclassmanagementapp.edit_teacher;

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

public class EditTeacherViewModelTest {
    private FakeTestRepository repository;
    private EditTeacherViewModel viewModel;
    private Teacher teacher;

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
        teacher = new Teacher("Jane Doe");
        viewModel = new EditTeacherViewModel(teacher, repository);
    }

    // method names are self documented :)
    @Test
    public void updateTeacher_updatesTeacherInRepoAndGeneratesUpdateCompleteEvent() {
        String updatedName = "John Doe";
        viewModel.setName(updatedName);

        viewModel.updateTeacher();

        List<Teacher> repoTeachers = repository.observeAllTeachers().getValue();
        assertThat(repoTeachers.size(), is(1));
        assertThat(repoTeachers.get(0).getName(), is(updatedName));
        Event<Boolean> event = viewModel.observeTeacherUpdatedEvent().getValue();
        assertThat(event.getContentIfNotHandled(), is(true));
    }
}
