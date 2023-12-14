package com.example.hw12springcore.note.service;

import com.example.hw12springcore.note.Note;
import com.example.hw12springcore.note.NoteDto;
import com.example.hw12springcore.note.NoteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceMapImpl implements NoteService {
    private final Map<Long, Note> noteMap = new LinkedHashMap<>();
    private final NoteMapper noteMapper;

    @Override
    public List<NoteDto> listAll() {
        return noteMap.values()
                .stream()
                .map(noteMapper::buildNoteDto)
                .toList();
    }

    @Override
    public NoteDto add(NoteDto noteDto) {
        Note note = noteMapper.buildNote(noteDto);
        note.setId(UUID.randomUUID().getLeastSignificantBits());
        noteMap.put(note.getId(), note);
        return noteMapper.buildNoteDto(note);
    }

    @Override
    public void deleteById(long id) {
        if (noteMap.get(id) == null) {
            log.error("deleteById: such id doesn't exist");
            throw new NoSuchElementException();
        }
        noteMap.remove(id);
    }

    @Override
    public void update(NoteDto noteDto) {
        Note note = noteMapper.buildNote(noteDto);
        if (noteMap.get(note.getId()) == null) {
            log.error("update: such note doesn't exist");
            throw new NoSuchElementException();
        }
        noteMap.replace(note.getId(), note);
    }

    @Override
    public NoteDto getById(long id) {
        if (noteMap.get(id) == null) {
            log.error("getById: such id doesn't exist");
            throw new NoSuchElementException();
        }
        return noteMapper.buildNoteDto(noteMap.get(id));
    }
}
