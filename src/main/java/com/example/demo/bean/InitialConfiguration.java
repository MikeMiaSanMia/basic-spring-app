package com.example.demo.bean;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitialConfiguration {
    @Autowired
    private NoteRepository noteRepository;

    @PostConstruct
    public void onStartUp() {
        noteRepository.save(new Note(
                "My note 1",
                LocalDate.now()
        ));
        noteRepository.save(new Note(
                "My note 2",
                LocalDate.now()
        ));
        noteRepository.save(new Note(
                "My note 3",
                LocalDate.now()
        ));
    }
}
