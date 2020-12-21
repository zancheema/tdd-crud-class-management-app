package com.example.tddclassmanagementapp.classroom;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.data.source.entities.Student;
import com.example.tddclassmanagementapp.data.source.entities.Teacher;

import java.util.List;

public class ClassRoomViewModel extends ViewModel {
    private final AppRepository repository;
    private final LiveData<ClassRoom> classRoom;
    private final LiveData<Teacher> teacher;
    public final LiveData<String> teacherName;
    private final LiveData<List<Student>> students;
    private final MutableLiveData<String> classRoomName =
            new MutableLiveData<>();

    public ClassRoomViewModel(AppRepository repository, String classRoomId, String name) {
        classRoomName.setValue(name);
        this.repository = repository;
        students = repository.observeStudentsByClassId(classRoomId);
        classRoom = repository.observeClassRoomById(classRoomId);
        teacher = Transformations.switchMap(classRoom, classRoom -> {
            if (classRoom.getTeacherId() == null) return null;
            return repository.observeTeacherById(classRoom.getTeacherId());
        });
        teacherName = Transformations.map(teacher, teacher -> {
            if (teacher == null) return "No Teacher";
            return teacher.getName();
        });
    }

    public LiveData<List<Student>> observeStudents() {
        return students;
    }

    public MutableLiveData<String> getClassRoomName() {
        return classRoomName;
    }

    public static class ClassRoomViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private AppRepository repository;
        private String classRoomId;
        private String classRoomName;

        public ClassRoomViewModelFactory(AppRepository repository, String classRoomId, String classRoomName) {
            this.repository = repository;
            this.classRoomId = classRoomId;
            this.classRoomName = classRoomName;
        }

        @SuppressWarnings("UNCHECKED_CAST")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ClassRoomViewModel(repository, classRoomId, classRoomName);
        }
    }
}
