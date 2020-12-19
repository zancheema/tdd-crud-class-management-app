package com.example.tddclassmanagementapp.create_classroom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;

public class CreateClassRoomViewModel extends ViewModel {
    private final AppRepository repository;
    private String name;
    private final MutableLiveData<Event<Boolean>> creationCompleteEvent =
            new MutableLiveData<>();

    public CreateClassRoomViewModel(AppRepository repository) {
        this.repository = repository;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void createClassRoom() {
        if (name == null || name.isEmpty()) return;

        repository.createClassRoom(new ClassRoom(name, null));
        generateCreationCompleteEvent();
    }

    private void generateCreationCompleteEvent() {
        creationCompleteEvent.setValue(new Event<>(true));
    }

    public LiveData<Event<Boolean>> observeCreationCompleteEvent() {
        return creationCompleteEvent;
    }
}
