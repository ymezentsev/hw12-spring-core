package com.example.hw12springcore.note.service;

import com.example.hw12springcore.note.NoteDto;

import java.util.List;

public interface NoteService {
    List<NoteDto> listAll();
    NoteDto add(NoteDto noteDto);
    void deleteById(long id);
    void update(NoteDto noteDto);
    NoteDto getById(long id);
}
