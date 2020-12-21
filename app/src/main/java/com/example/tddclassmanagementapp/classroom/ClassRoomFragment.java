package com.example.tddclassmanagementapp.classroom;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tddclassmanagementapp.MyApplication;
import com.example.tddclassmanagementapp.classroom.ClassRoomViewModel.ClassRoomViewModelFactory;
import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.databinding.FragmentClassRoomBinding;

public class ClassRoomFragment extends Fragment {

    private ClassRoom classRoom;
    private FragmentClassRoomBinding viewDataBinding;
    private ClassRoomViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentClassRoomBinding.inflate(inflater, container, false);
        ClassRoomFragmentArgs args = ClassRoomFragmentArgs.fromBundle(getArguments());
        String classRoomId = args.getClassRoomId();
        String name = args.getClassRoomName();
        viewModel = new ViewModelProvider(
                this, new ClassRoomViewModelFactory(
                ((MyApplication) getActivity().getApplication()).getRepository(), classRoomId, name
        )).get(ClassRoomViewModel.class);
        viewDataBinding.setViewmodel(this.viewModel);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewDataBinding.setLifecycleOwner(getViewLifecycleOwner());
        setUpListAdapter();
    }

    private void setUpListAdapter() {
        ClassRoomStudentsListAdapter adapter = new ClassRoomStudentsListAdapter();
        viewDataBinding.classRoomStudentList.setAdapter(adapter);
        viewModel.observeStudents().observe(getViewLifecycleOwner(), students -> {
            adapter.submitList(students);
        });
    }
}