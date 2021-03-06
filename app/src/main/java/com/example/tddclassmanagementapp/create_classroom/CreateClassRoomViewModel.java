package com.example.tddclassmanagementapp.create_classroom;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

public class CreateClassRoomViewModel extends ViewModel {
    private final AppRepository repository;
    private String name;
    private final MutableLiveData<Event<Boolean>> creationCompleteEvent =
            new MutableLiveData<>();
    private final LiveData<List<Teacher>> teachers;
    public final LiveData<List<String>> teacherNames;
    public final MutableLiveData<Integer> selectedTeacherIndex =
            new MutableLiveData<>();

    public CreateClassRoomViewModel(AppRepository repository) {
        this.repository = repository;
        teachers = repository.observeAllTeachers();
        teacherNames = getTeacherNames();
    }

    private LiveData<List<String>> getTeacherNames() {
        return Transformations.map(teachers, teachers -> {
            List<String> names = new ArrayList<>();
            for (Teacher t : teachers) names.add(t.getName());
            return names;
        });
    }

    public void setName(String name) {
        this.name = name;
    }

    public void createClassRoom() {
        if (name == null || name.isEmpty()) return;
        Integer index = selectedTeacherIndex.getValue();
        String teacherId = index == null ? null : teachers.getValue().get(index).getId();
        repository.createClassRoom(new ClassRoom(name, teacherId));
        generateCreationCompleteEvent();
    }

    private void generateCreationCompleteEvent() {
        creationCompleteEvent.setValue(new Event<>(true));
    }

    public LiveData<Event<Boolean>> observeCreationCompleteEvent() {
        return creationCompleteEvent;
    }

    public static class CreateClassRoomViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private final AppRepository repository;

        public CreateClassRoomViewModelFactory(AppRepository repository) {
            this.repository = repository;
        }

        @SuppressWarnings("UNCHECKED_CAST")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new CreateClassRoomViewModel(repository);
        }
    }
}
