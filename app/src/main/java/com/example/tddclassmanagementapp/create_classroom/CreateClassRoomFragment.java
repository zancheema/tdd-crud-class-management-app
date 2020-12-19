package com.example.tddclassmanagementapp.create_classroom;

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
import com.example.tddclassmanagementapp.databinding.FragmentCreateClassRoomBinding;

public class CreateClassRoomFragment extends Fragment {
    private CreateClassRoomViewModel viewModel;
    private FragmentCreateClassRoomBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentCreateClassRoomBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(
                this, new CreateClassRoomViewModel.CreateClassRoomViewModelFactory(
                ((MyApplication) requireActivity().getApplication()).getRepository()
        )).get(CreateClassRoomViewModel.class);

        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpNavigation();
        viewDataBinding.createClassRoomBtn.setOnClickListener(v -> {
            String name = viewDataBinding.createClassRoomName.getText().toString();
            viewModel.setName(name);
            viewModel.createClassRoom();
        });
    }

    private void setUpNavigation() {
        NavController navController = NavHostFragment.findNavController(this);
        viewModel.observeCreationCompleteEvent().observe(getViewLifecycleOwner(), new EventObserver<>(
                complete -> {
                    if (complete) navController.popBackStack(); // navigate back
                }
        ));
    }
}