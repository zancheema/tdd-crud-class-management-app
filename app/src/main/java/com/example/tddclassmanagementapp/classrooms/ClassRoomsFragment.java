package com.example.tddclassmanagementapp.classrooms;

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
import com.example.tddclassmanagementapp.R;
import com.example.tddclassmanagementapp.databinding.FragmentClassRoomsBinding;

import static com.example.tddclassmanagementapp.classrooms.ClassRoomsFragmentDirections.actionClassRoomsFragmentToCreateClassRoomFragment;

public class ClassRoomsFragment extends Fragment {
    private ClassRoomsViewModel viewModel;
    private FragmentClassRoomsBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentClassRoomsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(
                this, new ClassRoomsViewModel.ClassRoomsViewModelFactory(
                ((MyApplication) requireActivity().getApplication()).getRepository()
        )).get(ClassRoomsViewModel.class);
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

    /**
     * set up the adapter for classRoomList
     */
    private void setUpListAdapter() {
        ClassRoomsListAdapter listAdapter = new ClassRoomsListAdapter(viewModel);
        viewDataBinding.classRoomList.setAdapter(listAdapter);
        viewModel.observeClassRooms().observe(getViewLifecycleOwner(), classRooms -> {
            listAdapter.submitList(classRooms);
        });
    }

    /**
     * set up the navigation from this fragment to others
     */
    private void setUpNavigation() {
        NavController navController = NavHostFragment.findNavController(this);
        viewModel.observeAddClassRoomEvent().observe(getViewLifecycleOwner(), new Event.EventObserver<>(
                add -> {
                    if (add)
                        navController.navigate(actionClassRoomsFragmentToCreateClassRoomFragment());
                }
        ));
    }
}