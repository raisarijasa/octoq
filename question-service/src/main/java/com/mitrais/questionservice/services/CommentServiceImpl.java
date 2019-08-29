package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.CommentDto;
import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.models.Comment;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.repositories.CommentRepository;
import com.mitrais.questionservice.repositories.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Comment Service
 */
@Service
public class CommentServiceImpl implements CommentService {
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
     * @return true/false
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
    public CommentDto getCommentById(Long commentId) {
        Optional<Comment> optComment = findById(commentId);
        if (optComment.isPresent()) {
            CommentDto dto = new CommentDto();
            BeanUtils.copyProperties(optComment.get(), dto);
            return dto;
        } else {
            throw new DataNotFoundException("Comment not found!");
        }
    }

    /**
     * create comment
     *
     * @param body type CommentDto
     */
    @Override
    public void createComment(CommentDto body, Long postId) {
        postRepo.findById(postId)
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
    }

    /**
     * update comment
     *
     * @param body CommentDto
     */
    @Override
    public void updateComment(CommentDto body) {
        findById(body.getId())
                .map(comment -> {
                    comment.setDescription(body.getDescription());
                    comment.setModifiedDate(new Date());
                    save(comment);
                    return comment;
                }).orElseThrow(() -> new DataNotFoundException("Comment not found!"));
    }

    /**
     * delete comment by id
     *
     * @param commentId id of comment
     */
    @Override
    public void deleteComment(Long commentId) {
        Optional<Comment> optComment = findById(commentId);
        if (optComment.isPresent()) {
            deleteById(commentId);
        } else {
            throw new DataNotFoundException("Comment Not Found");
        }
    }
}
