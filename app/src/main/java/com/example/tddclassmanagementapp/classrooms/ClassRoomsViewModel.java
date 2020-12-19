package com.example.tddclassmanagementapp.classrooms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    public void addClassRoom() {
        observableAddClassRoomEvent.setValue(new Event<>(true));
    }

    public LiveData<Event<Boolean>> observeAddClassRoomEvent() {
        return observableAddClassRoomEvent;
    }

    public LiveData<List<ClassRoom>> observeClassRooms() {
        return repository.observeAllClassRooms();
    }
}
