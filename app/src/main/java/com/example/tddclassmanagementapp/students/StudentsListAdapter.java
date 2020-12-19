package com.example.tddclassmanagementapp.students;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tddclassmanagementapp.data.source.entities.Student;
import com.example.tddclassmanagementapp.databinding.StudentListItemBinding;

public class StudentsListAdapter extends ListAdapter<Student, StudentsListAdapter.ViewHolder> {

    public StudentsListAdapter() {
        super(new StudentDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final StudentListItemBinding binding;

        private ViewHolder(StudentListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Student s) {
            binding.setStudent(s);
            binding.executePendingBindings();
        }

        public static ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            StudentListItemBinding binding = StudentListItemBinding.inflate(inflater, parent, false);

            return new ViewHolder(binding);
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
