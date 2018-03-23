package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAllByOrderByDateDesc();
    }

    @GetMapping("/notes/{id}")
    public Note getNodeById(@PathVariable(value = "id") Long noteId) {
        // findById returns Optional if it is not present thro exception
        return noteRepository.findById(noteId)
                .orElseThrow(
                        () -> new ResourceNotFoundException()
                );
    }

    @PostMapping("/notes/save")
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @DeleteMapping("/notes/delete/{id}")
    public void deleteNote(@PathVariable(value = "id") Long noteId) {
        noteRepository.deleteById(noteId);
        noteRepository.findAllByOrderByDateDesc();
    }
}
