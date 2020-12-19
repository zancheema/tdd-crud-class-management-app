package com.example.tddclassmanagementapp.create_student;

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
import com.example.tddclassmanagementapp.databinding.FragmentCreateStudentBinding;

import static java.lang.Integer.parseInt;

public class CreateStudentFragment extends Fragment {
    private CreateStudentViewModel viewModel;
    private FragmentCreateStudentBinding viewDataBinding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentCreateStudentBinding.inflate(inflater, container, false);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(
                this,
                new CreateStudentViewModel.CreateStudentViewModelFactory(
                        ((MyApplication) requireActivity().getApplication()).getRepository()
                )
        ).get(CreateStudentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setUpCreateButton();
        setUpNavigation();
    }

    private void setUpNavigation() {
        viewModel.observeCreationCompleteEvent().observe(getViewLifecycleOwner(), new Event.EventObserver<>(
                created -> {
                    if (created) navController.popBackStack();
                }
        ));
    }

    private void setUpCreateButton() {
        viewDataBinding.createStudentBtn.setOnClickListener(v -> {
            int rollNo = parseInt(viewDataBinding.createStudentRollNo.getText().toString());
            String name = viewDataBinding.createStudentName.getText().toString();
            viewModel.setRollNo(rollNo);
            viewModel.setName(name);
            viewModel.createStudent();
        });
    }
}