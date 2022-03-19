package com.one_to_many.one_to_many.service;

import com.one_to_many.one_to_many.exception.ResourceNotFoundException;
import com.one_to_many.one_to_many.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsByTutorialId(Long tutorialId) throws ResourceNotFoundException;
   Comment getCommentsByTutorialId(Long id) throws ResourceNotFoundException;
    Comment createComment( Long tutorialId,Comment commentRequest) throws ResourceNotFoundException;
    Comment updateComment( long id,  Comment commentRequest) throws ResourceNotFoundException;
    void deleteComment( long id);
    List<Comment> deleteAllCommentsOfTutorial( Long tutorialId) throws ResourceNotFoundException;
}
