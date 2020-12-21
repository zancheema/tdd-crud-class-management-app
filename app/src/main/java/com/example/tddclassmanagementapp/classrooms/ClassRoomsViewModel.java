package com.example.tddclassmanagementapp.classrooms;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;

import java.util.List;

public class ClassRoomsViewModel extends ViewModel {

    private final AppRepository repository;

    public ClassRoomsViewModel(AppRepository repository) {
        this.repository = repository;
    }

    private final MutableLiveData<Event<Boolean>> observableAddClassRoomEvent =
            new MutableLiveData<>();

    private final MutableLiveData<Event<ClassRoom>> openClassRoomEvent =
            new MutableLiveData<>();

    public LiveData<Event<Boolean>> observeAddClassRoomEvent() {
        return observableAddClassRoomEvent;
    }

    public MutableLiveData<Event<ClassRoom>> observeOpenClassRoomEvent() {
        return openClassRoomEvent;
    }

    public LiveData<List<ClassRoom>> observeClassRooms() {
        return repository.observeAllClassRooms();
    }

    public void addClassRoom() {
        observableAddClassRoomEvent.setValue(new Event<>(true));
    }

    public void openClassRoom(ClassRoom c) {
        openClassRoomEvent.setValue(new Event<>(c));
    }

    public void deleteClassRoom(ClassRoom c) {
        repository.deleteClassRoom(c);
    }

    public static class ClassRoomsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private final AppRepository repository;

        public ClassRoomsViewModelFactory(AppRepository repository) {
            this.repository = repository;
        }

        @SuppressWarnings("UNCHECKED_CAST")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ClassRoomsViewModel(repository);
        }
    }
}
