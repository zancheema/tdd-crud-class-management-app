package com.example.tddclassmanagementapp.students;

import android.os.Bundle;

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
import com.example.tddclassmanagementapp.databinding.FragmentStudentsBinding;
import com.example.tddclassmanagementapp.students.StudentsViewModel.StudentsViewModelFactory;

import static com.example.tddclassmanagementapp.students.StudentsFragmentDirections.actionStudentsFragmentToCreateStudentFragment;

public class StudentsFragment extends Fragment {
    private StudentsViewModel viewModel;
    private FragmentStudentsBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentStudentsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(
                this,
                new StudentsViewModelFactory(
                        ((MyApplication) requireActivity().getApplication()).getRepository()
                )
        ).get(StudentsViewModel.class);
        viewDataBinding.setViewmodel(viewModel);

        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewDataBinding.setLifecycleOwner(getViewLifecycleOwner());
        setUpListAdapter();
        setUpNavigation();
    }

    private void setUpNavigation() {
        NavController navController = NavHostFragment.findNavController(this);
        viewModel.observeAddStudentEvent().observe(getViewLifecycleOwner(), new Event.EventObserver<>(
                add -> {
                    if (add)
                        navController.navigate(actionStudentsFragmentToCreateStudentFragment());
                }
        ));
    }

    private void setUpListAdapter() {
        StudentsListAdapter listAdapter = new StudentsListAdapter(viewModel);
        viewDataBinding.studentList.setAdapter(listAdapter);
        viewModel.observeStudents().observe(getViewLifecycleOwner(), students -> {
            listAdapter.submitList(students);
        });
    }
}