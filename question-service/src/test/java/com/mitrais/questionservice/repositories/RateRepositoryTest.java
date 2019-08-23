package com.mitrais.questionservice.repositories;

import com.mitrais.questionservice.models.*;
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
                .setTitle("Title 1")
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
    public void testUpdateQuestion_ReturnSuccess() {
        rateRepo.save(sampleRate);
        System.out.println("Test Data " + sampleRate);
        Optional<Rate> updateData = rateRepo.findById(sampleRate.getUserId());
        if (updateData.isPresent()) {
            updateData.get().setRating(0);
            rateRepo.save(updateData.get());
            Rate dbData = rateRepo.getOne(sampleRate.getUserId());
            System.out.println("Db Data " + dbData);
            Assert.assertTrue(dbData.getRating() != sampleRate.getRating());
        } else {
            Assert.fail("Data not found");
        }
    }

    @Test
    public void testDeleteById_ReturnSuccess() {
        rateRepo.save(sampleRate);
        rateRepo.deleteById(sampleRate.getUserId());
        Assert.assertFalse(rateRepo.existsById(sampleRate.getUserId()));
    }
}