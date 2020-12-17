package com.example.tddclassmanagementapp.data.source.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {
    @PrimaryKey
    @ColumnInfo(name = "roll_no")
    private int rollNo;

    @NonNull
    private String name;

    @ColumnInfo(name = "class_id")
    @Nullable
    private String classId;

    public Student(int rollNo, @NonNull String name, @NonNull String classId) {
        this.rollNo = rollNo;
        this.name = name;
        this.classId = classId;
    }

    public int getRollNo() {
        return rollNo;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getClassId() {
        return classId;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setClassId(@NonNull String classId) {
        this.classId = classId;
    }
}
