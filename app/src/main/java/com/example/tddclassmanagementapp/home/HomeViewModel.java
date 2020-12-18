package com.example.tddclassmanagementapp.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tddclassmanagementapp.Event;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<Event<HomeEvent>> navigationEvent = new MutableLiveData<>();

    public LiveData<Event<HomeEvent>> observeNavigationEvent() {
        return navigationEvent;
    }

    public void openStudents() {
        navigationEvent.setValue(new Event<>(HomeEvent.STUDENTS));
    }

    public void openTeachers() {
        navigationEvent.setValue(new Event<>(HomeEvent.TEACHERS));
    }

    public void openClassRooms() {
        navigationEvent.setValue(new Event(HomeEvent.CLASS_ROOMS));
    }
}
