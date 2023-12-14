package com.example.hw12springcore.note.service;

import com.example.hw12springcore.note.Note;
import com.example.hw12springcore.note.NoteDto;
import com.example.hw12springcore.note.NoteMapper;
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
    private final NoteMapper noteMapper;

    @Override
    public List<NoteDto> listAll() {
        return noteRepository.findAll()
                .stream()
                .map(noteMapper::buildNoteDto)
                .toList();
    }

    @Override
    public NoteDto add(NoteDto noteDto) {
        Note note = noteRepository.save(noteMapper.buildNote(noteDto));
        return noteMapper.buildNoteDto(note);
    }

    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void update(NoteDto noteDto) {
        noteRepository.save(noteMapper.buildNote(noteDto));
    }

    @Override
    public NoteDto getById(long id) {
        return noteMapper.buildNoteDto(noteRepository.getReferenceById(id));
    }
}
