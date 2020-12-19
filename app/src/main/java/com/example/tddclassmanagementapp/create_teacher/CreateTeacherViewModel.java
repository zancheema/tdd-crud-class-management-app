package com.example.tddclassmanagementapp.create_teacher;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

public class CreateTeacherViewModel extends ViewModel {
    private AppRepository repository;

    public CreateTeacherViewModel(AppRepository repository) {
        this.repository = repository;
    }

    private final MutableLiveData<Event<Boolean>> creationCompleteEvent =
            new MutableLiveData<>();
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void createTeacher() {
        if (name == null || name.isEmpty()) {
            return;
        }
        Teacher teacher = new Teacher(name);
        repository.createTeacher(teacher);
        generateCreationCompleteEvent();
    }

    private void generateCreationCompleteEvent() {
        creationCompleteEvent.setValue(new Event(true));
    }

    public LiveData<Event<Boolean>> observeCreationCompleteEvent() {
        return creationCompleteEvent;
    }
}
