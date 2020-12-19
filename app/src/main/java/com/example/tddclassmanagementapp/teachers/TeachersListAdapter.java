package com.example.tddclassmanagementapp.teachers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tddclassmanagementapp.data.source.entities.Teacher;
import com.example.tddclassmanagementapp.databinding.TeacherListItemBinding;

public class TeachersListAdapter extends ListAdapter<Teacher, TeachersListAdapter.ViewHolder> {

    public TeachersListAdapter() {
        super(new TeachersListAdapter.TeacherDiffCallback());
    }

    @NonNull
    @Override
    public TeachersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TeachersListAdapter.ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TeachersListAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TeacherListItemBinding binding;

        private ViewHolder(TeacherListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Teacher s) {
            binding.setTeacher(s);
            binding.executePendingBindings();
        }

        public static TeachersListAdapter.ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            TeacherListItemBinding binding = TeacherListItemBinding.inflate(inflater, parent, false);

            return new TeachersListAdapter.ViewHolder(binding);
        }
    }

    static class TeacherDiffCallback extends DiffUtil.ItemCallback<Teacher> {
        @Override
        public boolean areItemsTheSame(@NonNull Teacher oldItem, @NonNull Teacher newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Teacher oldItem, @NonNull Teacher newItem) {
            return oldItem.equals(newItem);
        }
    }
}