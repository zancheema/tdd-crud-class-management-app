package com.example.tddclassmanagementapp.teachers;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tddclassmanagementapp.Event.EventObserver;
import com.example.tddclassmanagementapp.MyApplication;
import com.example.tddclassmanagementapp.databinding.FragmentTeachersBinding;
import com.example.tddclassmanagementapp.teachers.TeachersViewModel.TeachersViewModelFactory;

import static com.example.tddclassmanagementapp.teachers.TeachersFragmentDirections.actionTeachersFragmentToCreateTeacherFragment;

public class TeachersFragment extends Fragment {
    private TeachersViewModel viewModel;
    private FragmentTeachersBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentTeachersBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(
                this,
                new TeachersViewModelFactory(
                        ((MyApplication) requireActivity().getApplication()).getRepository()
                )
        ).get(TeachersViewModel.class);
        viewDataBinding.setViewmodel(this.viewModel);

        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewDataBinding.setLifecycleOwner(getViewLifecycleOwner());
        setUpNavigation();
        setUpListAdapter();
    }

    private void setUpListAdapter() {
        TeachersListAdapter listAdapter = new TeachersListAdapter(viewModel);
        viewDataBinding.teacherList.setAdapter(listAdapter);
        viewModel.observeTeachers().observe(getViewLifecycleOwner(), teachers -> {
            listAdapter.submitList(teachers);
        });
    }

    private void setUpNavigation() {
        NavController navController = NavHostFragment.findNavController(this);
        viewModel.observeAddTeacherEvent().observe(getViewLifecycleOwner(), new EventObserver<>(
                add -> {
                    if (add)
                        navController.navigate(actionTeachersFragmentToCreateTeacherFragment());
                }
        ));
    }
}