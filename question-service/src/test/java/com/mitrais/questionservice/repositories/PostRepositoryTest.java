package com.mitrais.questionservice.repositories;

import com.mitrais.questionservice.models.Post;
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

    private Post sampleData;
    private Post sampleAnswer;

    @Before
    public void init() {
        sampleData = new Post();
        sampleData.setTitle("Title 1");
        sampleAnswer = new Post();
        sampleAnswer.setTitle("Answer 1");
    }

    @Test
    public void testFindByIdReturnSuccess() {
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
    public void testFindAllReturnSuccess() {
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
    public void testCreateQuestionSuccess() {
        repository.save(sampleData);
        Assert.assertTrue(repository.existsById(sampleData.getId()));
    }

    @Test
    public void testUpdateQuestionSuccess() {
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
    public void testDeleteByIdSuccess() {
        repository.save(sampleData);
        repository.deleteById(sampleData.getId());
        Assert.assertFalse(repository.existsById(sampleData.getId()));
    }

    public void testDeleteAnswerById() {
        Post answer = new Post();
        answer.setTitle("Answer 1");
        Set<Post> answers = new HashSet<>();
        answers.add(answer);
        sampleData.setAnswers(answers);
        repository.save(sampleData);
    }

    @Test
    public void testCreateAnswerShouldReturnSuccess() {
        Post answer = new Post();
        answer.setTitle("test 1");
        Post question = new Post();
        Set<Post> answers = new HashSet<>();
        answers.add(answer);
        question.setAnswers(answers);
        repository.save(question);

        Optional<Post> optQuestion = repository.findById(question.getId());
        if (optQuestion.isPresent()) {
            System.out.println("Test123" + optQuestion.get().getAnswers());
            Assert.assertThat(optQuestion.get().getAnswers().size(), is(1));
            System.out.println("Test123" + optQuestion.get().getAnswers());
        } else {
            Assert.fail("Fail");
        }

    }

    @Test
    public void testCreateAnswerShouldReturnSuccessGetOne() {
        Post answer = new Post();
        answer.setTitle("test 1");
        Post question = new Post();
        Set<Post> answers = new HashSet<>();
        answers.add(answer);
        question.setAnswers(answers);
        repository.save(question);

        Optional<Post> optQuestion = repository.findById(3L);
//        System.out.println(optQuestion.get());

    }
}