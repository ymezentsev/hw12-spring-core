package com.example.hw12springcore.note.service;

import com.example.hw12springcore.note.Note;
import com.example.hw12springcore.note.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class NoteServiceRepositoryImpl implements NoteService {
    private final NoteRepository noteRepository;

    @Override
    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note add(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Note note) {
        noteRepository.save(note);
    }

    @Override
    public Note getById(long id) {
        return noteRepository.getReferenceById(id);
    }
}
