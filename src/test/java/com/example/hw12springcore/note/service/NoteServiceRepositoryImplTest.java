package com.example.hw12springcore.note.service;

import com.example.hw12springcore.note.Note;
import com.example.hw12springcore.note.NoteDto;
import com.example.hw12springcore.note.NoteMapper;
import com.example.hw12springcore.note.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {NoteServiceRepositoryImpl.class, NoteMapper.class})
class NoteServiceRepositoryImplTest {
    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteMapper noteMapper;
    @MockBean
    private NoteRepository noteRepository;

    @Test
    void testListAll() {
        Note note1 = new Note(1L, "title1", "content1");
        Note note2 = new Note(2L, "title2", "content2");
        List<Note> notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);
        when(noteRepository.findAll()).thenReturn(notes);

        assertEquals(2, noteService.listAll().size());
    }

    @Test
    void testAdd() {
        Note note = new Note(1L, "title", "content");
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        NoteDto testNoteDto = new NoteDto(null, "", "");
        assertEquals(noteMapper.buildNoteDto(note), noteService.add(testNoteDto));
    }

    @Test
    void testGetById() {
        Note note = new Note(1L, "title", "content");
        when(noteRepository.getReferenceById(anyLong())).thenReturn(note);

        assertNotNull(noteService.getById(1));
    }
}