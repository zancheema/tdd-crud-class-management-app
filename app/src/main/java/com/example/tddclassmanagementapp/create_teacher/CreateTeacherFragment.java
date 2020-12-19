package com.example.tddclassmanagementapp.create_teacher;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.MyApplication;
import com.example.tddclassmanagementapp.R;
import com.example.tddclassmanagementapp.create_teacher.CreateTeacherViewModel.CreateTeacherViewModelFactory;
import com.example.tddclassmanagementapp.databinding.FragmentCreateTeacherBinding;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class CreateTeacherFragment extends Fragment {
    private CreateTeacherViewModel viewModel;
    private FragmentCreateTeacherBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentCreateTeacherBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(
                this, new CreateTeacherViewModelFactory(
                ((MyApplication) requireActivity().getApplication()).getRepository()
        )).get(CreateTeacherViewModel.class);

        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpNavigation();
        viewDataBinding.createTeacherBtn.setOnClickListener(v -> {
            String name = viewDataBinding.createTeacherName.getText().toString();
            viewModel.setName(name);
            viewModel.createTeacher();
        });
    }

    private void setUpNavigation() {
        NavController navController = findNavController(this);
        viewModel.observeCreationCompleteEvent().observe(getViewLifecycleOwner(), new Event.EventObserver<>(
                complete -> {
                    if (complete) navController.popBackStack();
                }
        ));
    }
}