package com.example.tddclassmanagementapp.util;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;

import java.util.List;

import static android.R.layout.simple_list_item_1;

public class DataBindingUtil {
    @BindingAdapter("app:spinner_items")
    public static void setSpinnerItems(Spinner spinner, List<String> items) {
        if (items == null) return;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(), simple_list_item_1, items);
        spinner.setAdapter(adapter);
    }
}
