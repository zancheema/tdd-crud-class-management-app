package com.example.tddclassmanagementapp.edit_teacher;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

public class EditTeacherViewModel extends ViewModel {
    private final Teacher teacher;
    private final AppRepository repository;
    private MutableLiveData<Event<Boolean>> teacherUpdatedEvent =
            new MutableLiveData<>();

    public EditTeacherViewModel(Teacher teacher, AppRepository repository) {
        this.teacher = teacher;
        this.repository = repository;
    }

    public void setName(String name) {
        teacher.setName(name);
    }

    public void updateTeacher() {
        repository.updateTeacher(teacher);
        teacherUpdatedEvent.setValue(new Event<>(true));
    }

    public LiveData<Event<Boolean>> observeTeacherUpdatedEvent() {
        return teacherUpdatedEvent;
    }
}
