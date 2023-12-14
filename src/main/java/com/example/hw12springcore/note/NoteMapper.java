package com.example.hw12springcore.note;

import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public NoteDto buildNoteDto(Note note) {
        return NoteDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }
    public Note buildNote(NoteDto noteDto) {
        return Note.builder()
                .id(noteDto.getId())
                .title(noteDto.getTitle())
                .content(noteDto.getContent())
                .build();
    }
    public Note buildNoteWithoutId(NoteDto noteDto) {
        return Note.builder()
                .title(noteDto.getTitle())
                .content(noteDto.getContent())
                .build();
    }
}
