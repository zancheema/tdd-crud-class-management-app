package com.example.tddclassmanagementapp;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tddclassmanagementapp.data.source.AppDatabase;
import com.example.tddclassmanagementapp.data.source.AppRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServiceLocator {
    private static ServiceLocator INSTANCE;
    private static int NUMBER_OF_THREADS = 4;
    private Executor executor;
    private AppRepository repository;

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized (ServiceLocator.class) {
                INSTANCE = new ServiceLocator();
            }
        }
        return INSTANCE;
    }

    private ServiceLocator() {

    }

    public AppRepository provideRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(
                context,
                AppDatabase.class, "tdd_db"
        ).build();
        executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        return new DefaultAppRepository(
                db.getStudentDao(),
                db.getTeacherDao(),
                db.getClassRoomDao(),
                executor
        );
    }
}
