package com.example.tddclassmanagementapp.edit_student;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.FakeTestRepository;
import com.example.tddclassmanagementapp.data.source.entities.Student;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EditStudentViewModelTest {
    private FakeTestRepository repository;
    private EditStudentViewModel viewModel;
    private Student student;

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
        student = new Student(1, "Jane Doe", null);
        viewModel = new EditStudentViewModel(student, repository);
    }

    @Test
    public void update_updatesStudentDataAndGeneratesUpdateCompleteEvent() {
        final int updatedRollNo = 2;
        viewModel.setRollNo(updatedRollNo);
        final String updatedName = "John Doe";
        viewModel.setName(updatedName);

        viewModel.updateStudent();
        final List<Student> repoStudents = repository.observeAllStudents().getValue();
        assertThat(repoStudents.size(), is(1));
        Student updatedStudent = repoStudents.get(0);
        assertThat(updatedStudent.getRollNo(), is(updatedRollNo));
        assertThat(updatedStudent.getName(), is(updatedName));
        final Event<Boolean> event = viewModel.observeUpdateCompleteEvent().getValue();
        assertThat(event.getContentIfNotHandled(), is(true));
    }
}
