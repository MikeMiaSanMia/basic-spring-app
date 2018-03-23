package com.example.demo;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.notNullValue;

import com.example.demo.controller.NoteController;
import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class NoteControllerTest {
    @Autowired
    private NoteController noteController;

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void shouldGetAllNotes() {
        // should create deleteAll() method to clean database and make sure that tests are independent
        //noteController.deleteAll()
        // deleteAll is in noteRepository -> another injection and usage of this method

        // GIVEN
        noteRepository.deleteAll();
        Note newNote = new Note("TEST 123", LocalDate.now());
        Note newNoteSecond = new Note("TEST 456", LocalDate.now());

        // WHEN
        Note savedNote = noteController.createNote(newNote);
        Note savedNoteSecond = noteController.createNote(newNoteSecond);
        List<Note> notes = noteController.getAllNotes();
        // previously was value 4L because first test was with creation is done and initial value 3L is incremented to 4L
        // now without method deleteAll()

        //THEN
        assertThat(notes, hasSize(2));
        //assertThat(notes, hasSize(4));
    }

    @Test
    public void shouldCreateNewTest() {
        // GIVEN
        Note newNote = new Note("TEST 123", LocalDate.now());

        // WHEN
        Note savedNote = noteController.createNote(newNote);

        // THEN
        assertNotNull(savedNote.getId());
        assertThat(savedNote.getId(), notNullValue());
        assertThat(savedNote.getId(), is(notNullValue()));
        assertThat(savedNote.getId(), is(4L));

    }

}
