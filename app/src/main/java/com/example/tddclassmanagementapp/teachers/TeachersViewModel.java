package com.example.tddclassmanagementapp.teachers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

import java.util.List;

public class TeachersViewModel extends ViewModel {
    private final AppRepository repository;

    public TeachersViewModel(AppRepository repository) {
        this.repository = repository;
    }

    private final MutableLiveData<Event<Boolean>> observableAddTeacherEvent =
            new MutableLiveData<>();

    public void addTeacher() {
        observableAddTeacherEvent.setValue(new Event<>(true));
    }

    public LiveData<Event<Boolean>> observeAddTeacherEvent() {
        return observableAddTeacherEvent;
    }

    public LiveData<List<Teacher>> observeTeachers() {
        return repository.observeAllTeachers();
    }
}
