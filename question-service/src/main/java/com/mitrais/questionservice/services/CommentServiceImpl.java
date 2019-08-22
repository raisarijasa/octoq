package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.CommentDto;
import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.models.Comment;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.repositories.CommentRepository;
import com.mitrais.questionservice.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Comment Service
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {
    private CommentRepository commentRepo;
    private PostRepository postRepo;

    /**
     * Comment service constructor
     *
     * @param commentRepo comment repository
     * @param postRepo    post repository
     */
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    /**
     * save comment data
     *
     * @param data generic object
     */
    @Override
    public void save(Comment data) {
        commentRepo.save(data);
    }

    /**
     * find Comment by Id
     *
     * @param id id of data
     * @return optional comment
     */
    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepo.findById(id);
    }

    /**
     * delete comment by id
     *
     * @param id of data
     */
    @Override
    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }

    /**
     * find all comment
     *
     * @return list of comment
     */
    @Override
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    /**
     * validate comment exist by id
     *
     * @param id id of data
     * @return
     */
    @Override
    public boolean existsById(Long id) {
        return commentRepo.existsById(id);
    }

    /**
     * get Comment by Id
     *
     * @param commentId id of comment
     * @return response entity object
     */
    @Override
    public ResponseEntity getCommentById(Long commentId) {
        List<Comment> comments = new ArrayList<>();
        Optional<Comment> optComment = findById(commentId);
        optComment.ifPresent(comments::add);
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                comments
        ));
    }

    /**
     * create comment
     *
     * @param body type CommentDto
     * @return response entity object
     */
    @Override
    public ResponseEntity createComment(CommentDto body) {
        postRepo.findById(body.getPostId())
                .map(question -> {
                    Set<Comment> comments = question.getComments();
                    Comment comment = new Comment()
                            .setUserId(body.getUserId())
                            .setDescription(body.getDescription())
                            .setCreatedDate(new Date())
                            .setModifiedDate(null)
                            .setStatus(Status.APPROVED);
                    comments.add(comment);
                    question.setComments(comments);
                    save(comment);
                    return question;
                }).orElseThrow(() -> new DataNotFoundException("Question not found"));

        return ok(getResponse(
                false,
                "00001",
                "A new comment has been created successfully",
                new ArrayList<>()
        ));
    }

    /**
     * update comment
     *
     * @param body CommentDto
     * @return response entity object
     */
    @Override
    public ResponseEntity updateComment(CommentDto body) {
        if (!postRepo.existsById(body.getPostId())) {
            throw new DataNotFoundException("Data not found!");
        }

        findById(body.getId())
                .map(comment -> {
                    comment.setDescription(body.getDescription());
                    comment.setModifiedDate(new Date());
                    save(comment);
                    return comment;
                }).orElseThrow(() -> new DataNotFoundException("Comment not found!"));

        return ok(getResponse(
                false,
                "00002",
                "The comment has been updated successfully",
                new ArrayList<>()
        ));
    }

    /**
     * delete comment by id
     *
     * @param commentId id of comment
     * @return response entity object
     */
    @Override
    public ResponseEntity deleteComment(Long commentId) {
        Optional<Comment> optComment = findById(commentId);
        if (optComment.isPresent()) {
            deleteById(commentId);
            return ok(getResponse(
                    false,
                    "00004",
                    "Comment has been deleted successfully",
                    new ArrayList<>()
            ));
        } else {
            throw new DataNotFoundException("Comment Not Found");
        }
    }
}
