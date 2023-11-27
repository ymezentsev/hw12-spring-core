package com.example.hw12springcore.note;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteServiceMapImpl implements NoteService {
    private final Map<Long, Note> noteMap = new LinkedHashMap<>();

    @Override
    public List<Note> listAll() {
        return noteMap.values()
                .stream()
                .toList();
   }

    @Override
    public Note add(Note note) {
        note.setId(UUID.randomUUID().getLeastSignificantBits());
        noteMap.put(note.getId(), note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        if(noteMap.get(id) == null){
            throw new NoSuchElementException();
        }
        noteMap.remove(id);
    }

    @Override
    public void update(Note note) {
        if(noteMap.get(note.getId()) == null){
            throw new NoSuchElementException();
        }
        noteMap.replace(note.getId(), note);
    }

    @Override
    public Note getById(long id) {
        if(noteMap.get(id) == null){
            throw new NoSuchElementException();
        }
        return noteMap.get(id);
    }
}
