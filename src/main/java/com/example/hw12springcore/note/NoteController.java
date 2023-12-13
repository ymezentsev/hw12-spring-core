package com.example.hw12springcore.note;

import com.example.hw12springcore.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView getNoteList() {
        ModelAndView result = new ModelAndView("note_list");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @PostMapping("/add")
    public String addNote(@RequestParam String title, @RequestParam String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);

        noteService.add(note);
        return "redirect:/note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView editNote(@RequestParam long id) {
        ModelAndView result = new ModelAndView("edit_note");
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String postEditNote(@RequestParam long id, @RequestParam String title, @RequestParam String content) {
        noteService.update(new Note(id, title, content));
        return "redirect:/note/list";
    }
}
