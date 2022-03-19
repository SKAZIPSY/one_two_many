package com.one_to_many.one_to_many.controller;


import com.one_to_many.one_to_many.exception.ResourceNotFoundException;
import com.one_to_many.one_to_many.model.Comment;
import com.one_to_many.one_to_many.repo.CommentRepository;
import com.one_to_many.one_to_many.repo.TutorialRepository;
import com.one_to_many.one_to_many.service.CommentServiceImpl;
import com.one_to_many.one_to_many.service.TutorialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TutorialServiceImpl tutorialService;

    @Autowired
    private CommentServiceImpl commentService;


    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) throws ResourceNotFoundException {
       List<Comment> comments = commentService.getAllCommentsByTutorialId(tutorialId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentsByTutorialId(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Comment comment = commentService.getCommentsByTutorialId(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                                 @RequestBody Comment commentRequest) throws ResourceNotFoundException {
       Comment comment = commentService.createComment(tutorialId , commentRequest);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) throws ResourceNotFoundException {


        return new ResponseEntity<>(commentService.updateComment(id,commentRequest), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        commentService.deleteComment(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) throws ResourceNotFoundException {
       List<Comment> comments = commentService.deleteAllCommentsOfTutorial(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
