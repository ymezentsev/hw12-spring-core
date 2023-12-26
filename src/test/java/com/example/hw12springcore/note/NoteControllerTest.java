package com.example.hw12springcore.note;

import com.example.hw12springcore.note.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
@ContextConfiguration
@WithMockUser(username = "user", authorities = {"USER"})
class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NoteService noteService;

    @Test
    void testGetNoteList() throws Exception {
        List<NoteDto> noteDtoList = new ArrayList<>();
        noteDtoList.add(new NoteDto(1L, "test", "test"));
        when(noteService.listAll()).thenReturn(noteDtoList);

        mockMvc.perform(get("/note/list"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/html;charset=UTF-8"),
                        view().name("note_list")
                );
    }

    @Test
    void testAddNote() throws Exception {
        NoteDto noteDto = new NoteDto(1L, "test", "test");
        when(noteService.add(any(NoteDto.class))).thenReturn(noteDto);

        mockMvc.perform(post("/note/add")
                        .with(csrf())
                        .param("id", "1")
                        .param("title", "test")
                        .param("content", "test"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/note/list")
                );
    }

    @Test
    void testDeleteNote() throws Exception {
        mockMvc.perform(post("/note/delete")
                        .with(csrf())
                        .param("id", "1"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/note/list")
                );
    }

    @Test
    void testEditNote() throws Exception {
        NoteDto noteDto = new NoteDto(1L, "test", "test");
        when(noteService.getById(anyLong())).thenReturn(noteDto);

        mockMvc.perform(get("/note/edit").param("id", "1"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/html;charset=UTF-8"),
                        view().name("edit_note")
                );
    }

    @Test
    void testPostEditNote() throws Exception {
        mockMvc.perform(post("/note/edit")
                        .with(csrf())
                        .param("id", "1")
                        .param("title", "test")
                        .param("content", "test"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/note/list")
                );
    }
}