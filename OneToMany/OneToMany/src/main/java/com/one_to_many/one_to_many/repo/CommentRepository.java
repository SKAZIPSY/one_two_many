package com.one_to_many.one_to_many.repo;

import com.one_to_many.one_to_many.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment ,Long> {
    List<Comment> findByTutorialId(Long postId);

    @Transactional
    List<Comment> deleteByTutorialId(long tutorialId);
}
