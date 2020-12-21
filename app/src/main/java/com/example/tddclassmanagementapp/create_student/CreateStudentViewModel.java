package com.example.tddclassmanagementapp.create_student;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.Student;

public class CreateStudentViewModel extends ViewModel {
    private AppRepository repository;
    private String name;
    private int rollNo = -1;
    private MutableLiveData<Event<Boolean>> emptyRollNoEvent = new MutableLiveData<>();
    private MutableLiveData<Event<Boolean>> emptyNameEvent = new MutableLiveData<>();
    private MutableLiveData<Event<Boolean>> creationCompleteEvent = new MutableLiveData<>();

    public CreateStudentViewModel(AppRepository repository) {
        this.repository = repository;
    }

    public LiveData<Event<Boolean>> observeEmptyNameEvent() {
        return emptyNameEvent;
    }

    public LiveData<Event<Boolean>> observeEmptyRollNoEvent() {
        return emptyRollNoEvent;
    }

    public void createStudent() {
        boolean flag = false;
        if (rollNo == -1) {
            generateEmptyRollNoEvent();
            flag = true;
        }
        if (name == null || name.isEmpty()) {
            generateEmptyNameEvent();
            flag = true;
        }
        if (flag) return;

        repository.createStudent(new Student(rollNo, name, null));
        generateCreationCompleteEvent();
    }

    private void generateEmptyRollNoEvent() {
        emptyRollNoEvent.setValue(new Event<>(true));
    }

    private void generateEmptyNameEvent() {
        emptyNameEvent.setValue(new Event(true));
    }

    private void generateCreationCompleteEvent() {
        creationCompleteEvent.setValue(new Event(true));
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LiveData<Event<Boolean>> observeCreationCompleteEvent() {
        return creationCompleteEvent;
    }

    static class CreateStudentViewModelFactory extends NewInstanceFactory {
        private AppRepository repository;

        public CreateStudentViewModelFactory(AppRepository repository) {
            this.repository = repository;
        }

        @SuppressWarnings("UNCHECKED_CAST")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new CreateStudentViewModel(repository);
        }
    }
}
