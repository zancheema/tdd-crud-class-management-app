package com.example.tddclassmanagementapp;

import android.content.Context;

import androidx.room.Room;

import com.example.tddclassmanagementapp.data.source.AppDatabase;
import com.example.tddclassmanagementapp.data.source.AppRepository;
import com.example.tddclassmanagementapp.data.source.DefaultAppRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Service Locator is a Singleton(https://en.wikipedia.org/wiki/Singleton_pattern)
 * It is used to contain a Repository which has only one Instance throughout the application
 * You can learn more about Service Locator Design Pattern from site:
 * https://en.wikipedia.org/wiki/Service_locator_pattern
 */
public class ServiceLocator {
    private static ServiceLocator INSTANCE;
    /* number of threads used for background work */
    private static int NUMBER_OF_THREADS = 4;
    /* executor is used to execute database work on background threads */
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
