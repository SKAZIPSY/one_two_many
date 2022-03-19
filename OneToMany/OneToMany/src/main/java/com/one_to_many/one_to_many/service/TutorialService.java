package com.one_to_many.one_to_many.service;

import com.one_to_many.one_to_many.exception.ResourceNotFoundException;
import com.one_to_many.one_to_many.model.Tutorial;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TutorialService {
    List<Tutorial> getAllTutorials(String title);

    Tutorial getTutorialById(long id) throws ResourceNotFoundException;

    Tutorial createTutorial(Tutorial tutorial);

    Tutorial updateTutorial(long id, Tutorial tutorial) throws ResourceNotFoundException;

    void deleteAllTutorials();
    void deleteTutorial( long id);
    List<Tutorial> findByPublished();
}
