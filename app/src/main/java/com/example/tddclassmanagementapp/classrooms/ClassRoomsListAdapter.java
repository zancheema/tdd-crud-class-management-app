package com.example.tddclassmanagementapp.classrooms;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tddclassmanagementapp.data.source.entities.ClassRoom;
import com.example.tddclassmanagementapp.databinding.ClassRoomListItemBinding;

class ClassRoomsListAdapter extends ListAdapter<ClassRoom, ClassRoomsListAdapter.ViewHolder> {

    public ClassRoomsListAdapter() {
        super(new ClassRoomsListAdapter.ClassRoomDiffCallback());
    }

    @NonNull
    @Override
    public ClassRoomsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ClassRoomsListAdapter.ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassRoomsListAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ClassRoomListItemBinding binding;

        private ViewHolder(ClassRoomListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ClassRoom s) {
            binding.setClassRoom(s);
            binding.executePendingBindings();
        }

        public static ClassRoomsListAdapter.ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ClassRoomListItemBinding binding = ClassRoomListItemBinding.inflate(inflater, parent, false);

            return new ClassRoomsListAdapter.ViewHolder(binding);
        }
    }

    static class ClassRoomDiffCallback extends DiffUtil.ItemCallback<ClassRoom> {
        @Override
        public boolean areItemsTheSame(@NonNull ClassRoom oldItem, @NonNull ClassRoom newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ClassRoom oldItem, @NonNull ClassRoom newItem) {
            return oldItem.equals(newItem);
        }
    }
}
