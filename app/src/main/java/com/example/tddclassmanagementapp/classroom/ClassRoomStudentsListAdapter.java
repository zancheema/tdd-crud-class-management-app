package com.example.tddclassmanagementapp.classroom;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tddclassmanagementapp.data.source.entities.Student;
import com.example.tddclassmanagementapp.databinding.ClassRoomStudentListItemBinding;

public class ClassRoomStudentsListAdapter extends ListAdapter<Student, ClassRoomStudentsListAdapter.ViewHolder> {

    public ClassRoomStudentsListAdapter() {
        super(new ClassRoomStudentsListAdapter.StudentDiffCallback());
    }

    @NonNull
    @Override
    public ClassRoomStudentsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ClassRoomStudentsListAdapter.ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassRoomStudentsListAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ClassRoomStudentListItemBinding binding;

        private ViewHolder(ClassRoomStudentListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Student s) {
            binding.setStudent(s);
            binding.executePendingBindings();
        }

        public static ClassRoomStudentsListAdapter.ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ClassRoomStudentListItemBinding binding = ClassRoomStudentListItemBinding.inflate(inflater, parent, false);

            return new ClassRoomStudentsListAdapter.ViewHolder(binding);
        }
    }

    static class StudentDiffCallback extends DiffUtil.ItemCallback<Student> {
        @Override
        public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.equals(newItem);
        }
    }
}
