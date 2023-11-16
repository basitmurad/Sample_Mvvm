package com.basit.samplemvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.basit.samplemvvm.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private NoteViewModel noteViewModel;
    AdapterClass adapterClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        noteViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NoteViewModel.class);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);

                intent.putExtra("type","addMode");
                startActivityForResult(intent, 1);
            }
        });


        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        binding.recycler.setHasFixedSize(true);


         adapterClass = new AdapterClass();
        binding.recycler.setAdapter(adapterClass);

        noteViewModel.getListLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapterClass.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    noteViewModel.Delete(adapterClass.getNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent intent = new Intent(MainActivity.this,DataInsertActivity.class);

                    intent.putExtra("type" ,"update");
                    intent.putExtra("title",adapterClass.getNote(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("description",adapterClass.getNote(viewHolder.getAdapterPosition()).getDescription());
                    intent.putExtra("id",adapterClass.getNote(viewHolder.getAdapterPosition()).getId());

                    startActivityForResult(intent,2);

                }


            }
        }).attachToRecyclerView(binding.recycler);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            // Adding a new note
            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("desc");
            Note note = new Note(title, desc);
            noteViewModel.Insert(note);
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 2) {
            // Updating an existing note
            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("desc");
            Note note = new Note(title, desc);
            note.setId(data.getIntExtra("id", 0));
            noteViewModel.Update(note);
            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
        }

        // Notify the adapter that the data set has changed
        adapterClass.notifyDataSetChanged();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1) {
//            String title = data.getStringExtra("title");
//            String desc = data.getStringExtra("desc");
//
//            Note note = new Note(title, desc);
//            noteViewModel.Insert(note);
//
//            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
//
//
//        }
//
//        else if (requestCode==2)
//        {
//            String title = data.getStringExtra("title");
//            String desc = data.getStringExtra("desc");
//            Note note = new Note(title, desc);
//            note.setId(data.getIntExtra("id",0));
//
//            noteViewModel.Update(note);
//            Toast.makeText(this, "Note Update", Toast.LENGTH_SHORT).show();
//
//        }
//    }
}