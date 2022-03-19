package com.one_to_many.one_to_many.service;

import com.one_to_many.one_to_many.exception.ResourceNotFoundException;
import com.one_to_many.one_to_many.model.Tutorial;
import com.one_to_many.one_to_many.repo.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;

    @Autowired
    public TutorialServiceImpl(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    @Override
    public List<Tutorial> getAllTutorials(String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();


        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        return tutorials;
    }

    @Override
    public Tutorial getTutorialById(long id) throws ResourceNotFoundException {
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
        return tutorial;
    }

    @Override
    public Tutorial createTutorial(Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
        return _tutorial;
    }


    @Override
    public Tutorial updateTutorial(long id, Tutorial tutorial) throws ResourceNotFoundException {
        Tutorial _tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());

        return _tutorial;
    }

    @Override
    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    @Override
    public void deleteTutorial(long id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    public List<Tutorial> findByPublished() {
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
        return tutorials;
    }
}
