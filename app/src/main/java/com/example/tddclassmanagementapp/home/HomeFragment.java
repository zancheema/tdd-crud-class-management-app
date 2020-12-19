package com.example.tddclassmanagementapp.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tddclassmanagementapp.Event;
import com.example.tddclassmanagementapp.R;
import com.example.tddclassmanagementapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private FragmentHomeBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this)
                .get(HomeViewModel.class);
        viewDataBinding.setViewmodel(this.viewModel);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewDataBinding.setLifecycleOwner(this.getViewLifecycleOwner());
        setUpNavigation();
    }

    private void setUpNavigation() {
        NavController navController = NavHostFragment.findNavController(this);
        viewModel.observeNavigationEvent().observe(getViewLifecycleOwner(), new Event.EventObserver<>(
                content -> {
                    switch (content) {
                        case STUDENTS:
                            navController.navigate(HomeFragmentDirections.actionHomeFragmentToStudentsFragment());
                            break;
                        case TEACHERS:
                            navController.navigate(HomeFragmentDirections.actionHomeFragmentToTeachersFragment());
                            break;
                        case CLASS_ROOMS:
                            navController.navigate(HomeFragmentDirections.actionHomeFragmentToClassRoomsFragment());
                            break;
                    }
                }
        ));
    }
}