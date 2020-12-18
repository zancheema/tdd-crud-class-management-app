package com.example.tddclassmanagementapp.home;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.tddclassmanagementapp.Event;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomeViewModelTest {
    private HomeViewModel viewModel;

    @Rule
    public final InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    /**
     * - Executes before each test
     * - Initializes viewModel
     */
    @Before
    public void init() {
        viewModel = new HomeViewModel();
    }

    @Test
    public void openStudents_setsUpHomeNavigationToStudent() {
        viewModel.openStudents();

        Event<HomeEvent> event = viewModel.observeNavigationEvent()
                .getValue();
        assertThat(event, is(notNullValue()));
        HomeEvent homeEvent = event.getContentIfNotHandled();
        assertThat(homeEvent, is(HomeEvent.STUDENTS));
    }

    @Test
    public void openTeachers_setsUpHomeNavigationToTeachers() {
        viewModel.openTeachers();

        Event<HomeEvent> event = viewModel.observeNavigationEvent()
                .getValue();
        assertThat(event, is(notNullValue()));
        HomeEvent homeEvent = event.getContentIfNotHandled();
        assertThat(homeEvent, is(HomeEvent.TEACHERS));
    }

    @Test
    public void openClassRooms_setsUpHomeNavigationToClassRooms() {
        viewModel.openClassRooms();

        Event<HomeEvent> event = viewModel.observeNavigationEvent()
                .getValue();
        assertThat(event, is(notNullValue()));
        HomeEvent homeEvent = event.getContentIfNotHandled();
        assertThat(homeEvent, is(HomeEvent.CLASS_ROOMS));
    }
}
