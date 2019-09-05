package com.mitrais.questionservice.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.models.Comment;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.repositories.CommentRepository;
import com.mitrais.questionservice.repositories.MessageRepository;
import com.mitrais.questionservice.repositories.PostRepository;

/**
 * Provide implementation of functionality to manipulate Comment input request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final MessageRepository messageRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo, MessageRepository messageRepository) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.messageRepository = messageRepository;
    }

    @Override
    public void save(Comment data) {
        commentRepo.save(data);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return commentRepo.existsById(id);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        Optional<Comment> optComment = findById(commentId);
        if (optComment.isPresent()) {
            return optComment.get();
        } else {
            throw new DataNotFoundException(messageRepository.COMMENT_NOT_FOUND);
        }
    }

    @Override
    public void createComment(Comment body, Long postId) {
        postRepo.findById(postId)
                .map(question -> {
                    Set<Comment> comments = question.getComments();
                    body.setId(null)
                            .setCreatedDate(new Date())
                            .setModifiedDate(null)
                            .setStatus(Status.APPROVED);
                    comments.add(body);
                    question.setComments(comments);
                    save(body);
                    return question;
                }).orElseThrow(() -> new DataNotFoundException(messageRepository.DATA_NOT_FOUND));
    }

    @Override
    public void updateComment(Comment body) {
        findById(body.getId())
                .map(comment -> {
                    comment.setDescription(body.getDescription());
                    comment.setModifiedDate(new Date());
                    save(comment);
                    return comment;
                }).orElseThrow(() -> new DataNotFoundException(messageRepository.COMMENT_NOT_FOUND));
    }

    @Override
    public void deleteComment(Long commentId) {
        try {
            deleteById(commentId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException(messageRepository.COMMENT_NOT_FOUND);
        }
    }
}
