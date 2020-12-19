package com.example.tddclassmanagementapp;

import android.app.Application;

import com.example.tddclassmanagementapp.data.source.AppRepository;

public class MyApplication extends Application {
    private AppRepository repository;

    public AppRepository getRepository() {
        return ServiceLocator.getInstance()
                .provideRepository(getApplicationContext());
    }
}
