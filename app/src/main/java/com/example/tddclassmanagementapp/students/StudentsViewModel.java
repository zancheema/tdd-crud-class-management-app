package com.example.tddclassmanagementapp.students;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.Student;

import java.util.List;

public class StudentsViewModel extends ViewModel {
    private final AppRepository repository;
    private final MutableLiveData<Event<Boolean>> addStudentEvent =
            new MutableLiveData<>();

    public StudentsViewModel(AppRepository repository) {
        this.repository = repository;
    }

    public void addStudent() {
        addStudentEvent.setValue(new Event<>(true));
    }

    public LiveData<Event<Boolean>> observeAddStudentEvent() {
        return addStudentEvent;
    }

    public LiveData<List<Student>> observeStudents() {
        return repository.observeAllStudents();
    }

    public static class StudentsViewModelFactory extends NewInstanceFactory {
        private AppRepository repository;

        public StudentsViewModelFactory(AppRepository repository) {
            this.repository = repository;
        }

        @SuppressWarnings("UNCHECKED_CAST")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new StudentsViewModel(repository);
        }
    }
}
