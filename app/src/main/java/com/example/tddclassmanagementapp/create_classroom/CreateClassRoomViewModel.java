package com.example.tddclassmanagementapp.create_classroom;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.classrooms.ClassRoomsViewModel;
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

    public static class CreateClassRoomViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private AppRepository repository;

        public CreateClassRoomViewModelFactory(AppRepository repository) {
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
