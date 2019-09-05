package com.mitrais.questionservice.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.models.Rate;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.models.Type;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RateRepositoryTest {

    @Autowired
    private RateRepository rateRepo;

    @Autowired
    private PostRepository postRepo;

    private Rate sampleRate;
    private Post samplePost;

    @Before
    public void setUp() throws Exception {
        sampleRate = new Rate()
                .setUserId("1")
                .setRating(1);
        samplePost = new Post()
                .setUserId("1")
                .setTitle("Title 1")
                .setDescription("Description 1")
                .setType(Type.QUESTION)
                .setStatus(Status.APPROVED)
                .setAnswers(new HashSet<>())
                .setComments(new HashSet<>())
                .setRates(new HashSet<>());
        postRepo.save(samplePost);
    }

    @Test
    public void testCreateRate_ReturnSuccess() {
        Optional<Post> optQuestion = postRepo.findById(samplePost.getId());
        if (optQuestion.isPresent()) {
            Set<Rate> rates = optQuestion.get().getRates();
            rates.add(sampleRate);
            samplePost.setRates(rates);
            rateRepo.save(sampleRate);
            Assert.assertTrue(rateRepo.existsById(sampleRate.getUserId()));
        } else {
            Assert.fail("Fail");
        }
    }

    @Test
    public void testFindById_ReturnSuccess() {
        rateRepo.save(sampleRate);
        Optional<Rate> optPost = rateRepo.findById(sampleRate.getUserId());
        System.out.println("Test Data " + sampleRate);
        if (optPost.isPresent()) {
            System.out.println("DB Data " + optPost.get());
            Assert.assertEquals(optPost.get().getUserId(), sampleRate.getUserId());
        } else {
            Assert.fail("Data Not found");
        }
    }

    @Test
    public void testFindAll_ReturnSuccess() {
        rateRepo.save(sampleRate);
        List<Rate> rates = rateRepo.findAll();
        System.out.println("Test Data " + sampleRate);
        if (rates.size() > 0) {
            System.out.println("DB Data " + rates.get(0));
            Assert.assertEquals(sampleRate, rates.get(0));
        } else {
            Assert.fail("Data Not found");
        }
    }

    @Test
    public void testUpdateRate_ReturnSuccess() {
        rateRepo.save(sampleRate);
        sampleRate.setRating(0);
        rateRepo.save(sampleRate);
        Assert.assertEquals(sampleRate.getRating(), 0);
        Assert.assertNotEquals(sampleRate.getRating(), 1);
    }

    @Test
    public void testDeleteById_ReturnSuccess() {
        rateRepo.save(sampleRate);
        rateRepo.deleteById(sampleRate.getUserId());
        Assert.assertFalse(rateRepo.existsById(sampleRate.getUserId()));
    }
}