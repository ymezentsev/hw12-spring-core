package com.example.hw12springcore.note.service;

import com.example.hw12springcore.note.NoteDto;
import com.example.hw12springcore.note.NoteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceMapImplTest {
    private NoteService noteService;

    @BeforeEach
    void init() {
        noteService = new NoteServiceMapImpl(new NoteMapper());
    }

    @Test
    void testListAll() {
        List<NoteDto> expectedList = new ArrayList<>();
        NoteDto noteDto1 = new NoteDto(null, "Note1", "New note1");
        NoteDto noteDto2 = new NoteDto(null, "Note2", "New note2");

        expectedList.add(noteService.add(noteDto1));
        expectedList.add(noteService.add(noteDto2));

        assertEquals(expectedList, noteService.listAll());
    }

    @Test
    void testAdd() {
        NoteDto noteDto = new NoteDto(null, "Note1", "New note1");
        NoteDto actualNoteDto = noteService.add(noteDto);

        assertAll(() -> assertEquals(noteDto.getTitle(), actualNoteDto.getTitle()),
                () -> assertEquals(noteDto.getContent(), actualNoteDto.getContent()),
                () -> assertNotNull(actualNoteDto.getId()));
    }

    @Test
    void testDeleteById() {
        NoteDto noteDto = new NoteDto(null, "Note1", "New note1");
        NoteDto actualNoteDto = noteService.add(noteDto);

        noteService.deleteById(actualNoteDto.getId());
        assertThrows(NoSuchElementException.class, () -> noteService.getById(actualNoteDto.getId()));
    }

    @Test
    void testThatDeleteByIdThrowException() {
        assertThrows(NoSuchElementException.class, () -> noteService.deleteById(1));
    }

    @Test
    void testUpdate() {
        NoteDto noteDto = new NoteDto(null, "Note1", "New note1");
        NoteDto actualNoteDto = noteService.add(noteDto);

        String expectedTittle = "New tittle";
        actualNoteDto.setTitle(expectedTittle);
        noteService.update(actualNoteDto);

        assertEquals(expectedTittle, noteService.getById(actualNoteDto.getId()).getTitle());
    }

    @Test
    void testThatUpdateThrowException() {
        NoteDto noteDto = new NoteDto(1L, "Note1", "New note1");

        assertThrows(NoSuchElementException.class, () -> noteService.update(noteDto));
    }

    @Test
    void testGetById() {
        NoteDto noteDto = new NoteDto(null, "Note1", "New note1");
        NoteDto actualNoteDto = noteService.add(noteDto);

        assertEquals(actualNoteDto, noteService.getById(actualNoteDto.getId()));
    }

    @Test
    void testThatGetByIdThrowException() {
        assertThrows(NoSuchElementException.class, () -> noteService.getById(1));
    }

}
