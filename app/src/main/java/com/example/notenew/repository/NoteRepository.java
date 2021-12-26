package com.example.notenew.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.notenew.database.Note;
import com.example.notenew.database.NoteDao;
import com.example.notenew.database.NoteRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private final NoteDao mNotesDao;
    private final ExecutorService executorService;
    public NoteRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNotesDao = db.noteDao();
    }
    public LiveData<List<Note>> getAllNotes() {
        return mNotesDao.getAllNotes();
    }

    public void insert(final Note note) {
        executorService.execute(() -> mNotesDao.insert(note));
    }

    public void delete(final Note note){
        executorService.execute(() -> mNotesDao.delete(note));
    }

    public void update(final Note note){
        executorService.execute(() -> mNotesDao.update(note));
    }

}
