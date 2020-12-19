package com.example.tddclassmanagementapp.edit_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.Student;

public class EditStudentViewModel extends ViewModel {

    private final Student student;
    private final AppRepository repository;
    private MutableLiveData<Event<Boolean>> updateCompleteEvent =
            new MutableLiveData<>();

    public EditStudentViewModel(Student student, AppRepository repository) {
        this.student = student;
        this.repository = repository;
    }

    public void setRollNo(int rollNo) {
        student.setRollNo(rollNo);
    }

    public void setName(String name) {
        student.setName(name);
    }

    public void updateStudent() {
        repository.updateStudent(student);
        generateUpdateCompleteEvent();
    }

    private void generateUpdateCompleteEvent() {
        updateCompleteEvent.setValue(new Event<>(true));
    }

    public LiveData<Event<Boolean>> observeUpdateCompleteEvent() {
        return updateCompleteEvent;
    }
}
