package com.example.tddclassmanagementapp.edit_classroom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;

public class EditClassRoomViewModel {
    private final ClassRoom classRoom;
    private final AppRepository repository;
    private MutableLiveData<Event<Boolean>> updateCompleteEvent =
            new MutableLiveData<>();

    public EditClassRoomViewModel(ClassRoom classRoom, AppRepository repository) {
        this.classRoom = classRoom;
        this.repository = repository;
    }

    /*
    Removed redundant name field
     */
    public void updateClassRoom() {
        repository.updateClassRoom(classRoom);
        updateCompleteEvent.setValue(new Event<>(true));
    }

    public void setName(String name) {
        classRoom.setName(name);
    }

    public LiveData<Event<Boolean>> observeUpdateCompleteEvent() {
        return updateCompleteEvent;
    }
}
