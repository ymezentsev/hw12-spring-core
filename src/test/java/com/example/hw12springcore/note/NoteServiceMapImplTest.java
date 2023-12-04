package com.example.hw12springcore.note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceMapImplTest {
    private NoteService noteService;

    @BeforeEach
    void init() {
        noteService = new NoteServiceMapImpl();
    }

    @Test
    void testListAll() {
        List<Note> expectedList = new ArrayList<>();
        Note note1 = new Note(null, "Note1", "New note1");
        Note note2 = new Note(null, "Note2", "New note2");

        expectedList.add(noteService.add(note1));
        expectedList.add(noteService.add(note2));

        assertEquals(expectedList, noteService.listAll());
    }

    @Test
    void testAdd() {
        Note note = new Note(null, "Note1", "New note1");

        assertEquals(note, noteService.add(note));
    }

    @Test
    void testDeleteById() {
        Note note = new Note(null, "Note1", "New note1");
        noteService.add(note);

        noteService.deleteById(note.getId());
        assertThrows(NoSuchElementException.class, ()->noteService.getById(note.getId()));
    }

    @Test
    void testThatDeleteByIdThrowException() {
        assertThrows(NoSuchElementException.class, ()->noteService.deleteById(1));
    }

    @Test
    void testUpdate() {
        Note note = new Note(null, "Note1", "New note1");
        noteService.add(note);

        String expectedTittle = "New tittle";
        note.setTitle(expectedTittle);
        noteService.update(note);

        assertEquals(expectedTittle, noteService.getById(note.getId()).getTitle());
    }

    @Test
    void testThatUpdateThrowException() {
        Note note = new Note(1L, "Note1", "New note1");

        assertThrows(NoSuchElementException.class, ()->noteService.update(note));
    }

    @Test
    void testGetById() {
        Note note = new Note(null, "Note1", "New note1");
        noteService.add(note);

        assertEquals(note, noteService.getById(note.getId()));
    }

    @Test
    void testThatGetByIdThrowException() {
        assertThrows(NoSuchElementException.class, ()->noteService.getById(1));
    }

}