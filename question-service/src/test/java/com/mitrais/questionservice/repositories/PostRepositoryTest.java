package com.mitrais.questionservice.repositories;

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

import static org.hamcrest.Matchers.is;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    private Post sampleData, sampleDataBlocked, sampleAnswer;

    @Before
    public void init() {
        sampleData = new Post()
                .setTitle("Title 1")
                .setType(Type.QUESTION)
                .setStatus(Status.APPROVED)
                .setAnswers(new HashSet<>())
                .setComments(new HashSet<>())
                .setRates(new HashSet<>());
        sampleDataBlocked = new Post()
                .setTitle("Title 1")
                .setType(Type.QUESTION)
                .setStatus(Status.BLOCKED)
                .setAnswers(new HashSet<>())
                .setComments(new HashSet<>())
                .setRates(new HashSet<>());
        sampleAnswer = new Post()
                .setTitle("Answer 1")
                .setType(Type.ANSWER)
                .setAnswers(new HashSet<>())
                .setComments(new HashSet<>())
                .setRates(new HashSet<>());
    }

    @Test
    public void testFindById_ReturnSuccess() {
        repository.save(sampleData);
        Optional<Post> optPost = repository.findById(sampleData.getId());
        System.out.println("Test Data " + sampleData);
        if (optPost.isPresent()) {
            System.out.println("DB Data " + optPost.get());
            Assert.assertEquals(optPost.get().getId(), sampleData.getId());
        } else {
            Assert.fail("Data Not found");
        }
    }

    @Test
    public void testFindAll_ReturnSuccess() {
        repository.save(sampleData);
        List<Post> posts = repository.findAll();
        System.out.println("Test Data " + sampleData);
        if (posts.size() > 0) {
            System.out.println("DB Data " + posts.get(0));
            Assert.assertEquals(sampleData, posts.get(0));
        } else {
            Assert.fail("Data Not found");
        }
    }

    @Test
    public void testFindByTypeAndStatus_ReturnSuccess() {
        repository.save(sampleData);
        repository.save(sampleDataBlocked);
        List<Post> posts = repository.findAll();
        List<Post> postValid = repository.findByTypeAndStatus(Type.QUESTION, Status.APPROVED);
        Assert.assertThat(postValid.size(), is(1));
        Assert.assertNotEquals(posts.size(), postValid);
    }

    @Test
    public void testCreateQuestion_ReturnSuccess() {
        repository.save(sampleData);
        Assert.assertTrue(repository.existsById(sampleData.getId()));
    }

    @Test
    public void testUpdateQuestion_ReturnSuccess() {
        repository.save(sampleData);
        System.out.println("Test Data " + sampleData);
        Optional<Post> updateData = repository.findById(sampleData.getId());
        if (updateData.isPresent()) {
            updateData.get().setTitle("Title 2");
            repository.save(updateData.get());
            Post dbData = repository.getOne(sampleData.getId());
            System.out.println("Db Data " + dbData);
            Assert.assertTrue(dbData.getTitle().equalsIgnoreCase("Title 2"));
        } else {
            Assert.fail("Data not found");
        }
    }

    @Test
    public void testDeleteById_ReturnSuccess() {
        repository.save(sampleData);
        repository.deleteById(sampleData.getId());
        Assert.assertFalse(repository.existsById(sampleData.getId()));
    }

    public void testDeleteAnswerById_ReturnSuccess() {
        Set<Post> answers = new HashSet<>();
        answers.add(sampleAnswer);
        sampleData.setAnswers(answers);
        repository.save(sampleData);
    }

    @Test
    public void testCreateAnswerShould_ReturnSuccess() {
        repository.save(sampleData);
        Optional<Post> optQuestion = repository.findById(sampleData.getId());
        if (optQuestion.isPresent()) {
            Set<Post> answers = new HashSet<>();
            answers.add(sampleAnswer);
            sampleData.setAnswers(answers);
            repository.save(sampleAnswer);
        }

        Optional<Post> optResult = repository.findById(sampleData.getId());
        if (optResult.isPresent()) {
            System.out.println("Test123" + optResult.get().getAnswers());
            Assert.assertThat(optResult.get().getAnswers().size(), is(1));
            System.out.println("Test123" + optResult.get().getAnswers());
        } else {
            Assert.fail("Fail");
        }
    }

    @Test
    public void testCreateAnswerShould_ReturnSuccessGetOne() {
        repository.save(sampleData);
        Post post = repository.getOne(sampleData.getId());
        Set<Post> answers = new HashSet<>();
        answers.add(sampleAnswer);
        post.setAnswers(answers);
        repository.save(sampleAnswer);

        Post question = repository.getOne(sampleData.getId());
        Assert.assertThat(question.getAnswers().size(), is(1));
    }
}