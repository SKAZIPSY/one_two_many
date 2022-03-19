package com.one_to_many.one_to_many.service;

import com.one_to_many.one_to_many.exception.ResourceNotFoundException;
import com.one_to_many.one_to_many.model.Comment;
import com.one_to_many.one_to_many.repo.CommentRepository;
import com.one_to_many.one_to_many.repo.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TutorialRepository tutorialRepository;

    @Override
    public List<Comment> getAllCommentsByTutorialId(Long tutorialId) throws ResourceNotFoundException {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        List<Comment> comments = commentRepository.findByTutorialId(tutorialId);

        return comments;
    }

    @Override
    public Comment getCommentsByTutorialId(Long id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));
        return comment;
    }

    @Override
    public Comment createComment(Long tutorialId, Comment commentRequest) throws ResourceNotFoundException {
        Comment comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
            commentRequest.setTutorial(tutorial);
            return commentRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        return comment;
    }

    @Override
    public Comment updateComment(long id, Comment commentRequest) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

        comment.setContent(commentRequest.getContent());
        return comment;
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }


    @Override
    public List<Comment> deleteAllCommentsOfTutorial(Long tutorialId) throws ResourceNotFoundException {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        List<Comment> comments=commentRepository.deleteByTutorialId(tutorialId);
        return comments;
    }
}
