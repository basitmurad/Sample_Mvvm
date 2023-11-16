package com.basit.samplemvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepositoryClass noteRepositoryClass;
    private LiveData<List<Note>> listLiveData;

     public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRepositoryClass = new NoteRepositoryClass(application);
        listLiveData = noteRepositoryClass.getAllData();


    }


    public void Insert(Note note)
    {
        noteRepositoryClass.insertData(note);
    }

    public void Delete(Note note)
    {
        noteRepositoryClass.deleteData(note);
    }
    public void Update(Note note)
    {
        noteRepositoryClass.updateData(note);
    }
    public LiveData<List<Note>> getListLiveData()

    {

        return listLiveData;
    }
}
