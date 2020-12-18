package com.example.tddclassmanagementapp.create_student;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.FakeAppRepository;
import com.example.tddclassmanagementapp.data.source.entities.Student;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class CreateStudentViewModelTest {
    private FakeAppRepository repository;
    private CreateStudentViewModel viewModel;

    @Rule
    public final InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    /**
     * - Executes before each Test
     * - Initializes viewModel
     */
    @Before
    public void init() {
        repository = new FakeAppRepository();
        viewModel = new CreateStudentViewModel(repository);
    }

    @Test
    public void createStudent_rollNoIsEmpty_setsUpEmptyRollNoEvent() {
        // Given: create a new student
        viewModel.createStudent();

        // When: roll no is empty
        Event<Boolean> event = viewModel.observeEmptyRollNoEvent().getValue();

        // Then: emptyRollNoEvent is generated
        assertThat(event, is(notNullValue()));
        assertThat(event.getContentIfNotHandled(), is(true));
    }

    @Test
    public void createStudent_nameIsEmpty_setsUpEmptyNameEvent() {
        // Given: create a new student
        viewModel.createStudent();

        // When: name is empty
        Event<Boolean> event = viewModel.observeEmptyNameEvent().getValue();

        // Then: emptyNameEvent is generated
        assertThat(event, is(notNullValue()));
        assertThat(event.getContentIfNotHandled(), is(true));
    }

    @Test
    public void createStudent_validRollNoAndName_savesNameInRepositoryAndGeneratesCreationCompleteEvent() {
        // Given: valid name and roll no.
        Student s = new Student(1, "Jane Doe", null);
        viewModel.setRollNo(s.getRollNo());
        viewModel.setName(s.getName());

        // When: create a new student
        viewModel.createStudent();

        // Then: saves a new student in the repositoy and generates creationCompleteEvent
        List<Student> repoStudents = repository.observeAllStudents().getValue();
        assertThat(repoStudents, is(notNullValue()));
        assertThat(repoStudents, contains(s));
        Event<Boolean> event = viewModel.observeCreationCompleteEvent().getValue();
        assertThat(event.getContentIfNotHandled(), is(true));
    }
}
