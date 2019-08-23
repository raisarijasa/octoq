package com.mitrais.questionservice.repositories;

import com.mitrais.questionservice.models.Comment;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.models.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private PostRepository postRepo;
    private Comment sampleComment;
    private Post samplePost;

    @Before
    public void setUp() throws Exception {
        sampleComment = new Comment()
                .setUserId("1")
                .setDescription("Comment 1");
        samplePost = new Post()
                .setTitle("Title 1")
                .setType(Type.QUESTION)
                .setStatus(Status.APPROVED)
                .setAnswers(new HashSet<>())
                .setComments(new HashSet<>())
                .setRates(new HashSet<>());
        postRepo.save(samplePost);
    }

    @Test
    public void testCreateComment_ReturnSuccess() {
        Optional<Post> optQuestion = postRepo.findById(samplePost.getId());
        if (optQuestion.isPresent()) {
            Set<Comment> comments = optQuestion.get().getComments();
            comments.add(sampleComment);
            samplePost.setComments(comments);
            commentRepo.save(sampleComment);
            Assert.assertTrue(commentRepo.existsById(sampleComment.getId()));
        } else {
            Assert.fail("Fail");
        }
    }

    @Test
    public void testFindById_ReturnSuccess() {
        commentRepo.save(sampleComment);
        Optional<Comment> optPost = commentRepo.findById(sampleComment.getId());
        System.out.println("Test Data " + sampleComment);
        if (optPost.isPresent()) {
            System.out.println("DB Data " + optPost.get());
            Assert.assertEquals(optPost.get().getId(), sampleComment.getId());
        } else {
            Assert.fail("Data Not found");
        }
    }

    @Test
    public void testFindAll_ReturnSuccess() {
        commentRepo.save(sampleComment);
        List<Comment> posts = commentRepo.findAll();
        System.out.println("Test Data " + sampleComment);
        if (posts.size() > 0) {
            System.out.println("DB Data " + posts.get(0));
            Assert.assertEquals(sampleComment, posts.get(0));
        } else {
            Assert.fail("Data Not found");
        }
    }

    @Test
    public void testUpdateQuestion_ReturnSuccess() {
        commentRepo.save(sampleComment);
        System.out.println("Test Data " + sampleComment);
        Optional<Comment> updateData = commentRepo.findById(sampleComment.getId());
        if (updateData.isPresent()) {
            updateData.get().setDescription("Comment 2");
            commentRepo.save(updateData.get());
            Comment dbData = commentRepo.getOne(sampleComment.getId());
            System.out.println("Db Data " + dbData);
            Assert.assertTrue(dbData.getDescription().equalsIgnoreCase("Comment 2"));
        } else {
            Assert.fail("Data not found");
        }
    }

    @Test
    public void testDeleteById_ReturnSuccess() {
        commentRepo.save(sampleComment);
        commentRepo.deleteById(sampleComment.getId());
        Assert.assertFalse(commentRepo.existsById(sampleComment.getId()));
    }
}