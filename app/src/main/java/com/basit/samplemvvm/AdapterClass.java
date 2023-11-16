package com.basit.samplemvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.basit.samplemvvm.databinding.ItemLayoutBinding;

public class AdapterClass extends ListAdapter<Note , AdapterClass.ViewHolder> {


    public AdapterClass() {
        super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>() {
    @Override
    public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getTitle().equals(newItem.getTitle())
                && oldItem.getDescription().equals(newItem.getDescription());
    }
};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    Note note = getItem(position);

    holder.itemLayoutBinding.textViewTitle.setText(note.getTitle());
    holder.itemLayoutBinding.textViewDescription.setText(note.getDescription());

    }

    public Note getNote(int position)
    {
            return  getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemLayoutBinding itemLayoutBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemLayoutBinding =ItemLayoutBinding.bind(itemView);
        }
    }
}
