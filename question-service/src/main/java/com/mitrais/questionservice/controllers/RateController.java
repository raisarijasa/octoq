package com.mitrais.questionservice.controllers;

import javax.validation.Valid;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mitrais.questionservice.controllers.requests.RateRequest;
import com.mitrais.questionservice.models.Rate;
import com.mitrais.questionservice.repositories.MessageRepository;
import com.mitrais.questionservice.services.RateService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Provide functionality to manipulate Rate data request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@RestController
@RequestMapping("/rates")
public class RateController extends BaseController<Rate> {
    private final RateService rateService;
    private final MessageRepository messageRepository;

    @Autowired
    public RateController(RateService rateService, MessageRepository messageRepository) {
        this.rateService = rateService;
        this.messageRepository = messageRepository;
    }

    /**
     * Provide functionality to create and update rate for a question.
     *
     * @param request type Rate
     * @return response entity object
     */
    @PostMapping()
    public ResponseEntity createRate(@Valid @RequestBody RateRequest request) {
        Rate rate = new Rate();
        BeanUtils.copyProperties(request, rate);
        rateService.createRate(rate, request.getPostId());
        return createRateResponse();
    }

    /**
     * Provide functionality to delete rate.
     *
     * @param userId of the rate
     * @return response entity
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteRate(@PathVariable String userId) {
        if (userId == null || userId.equalsIgnoreCase("0")) {
            return idMandatoryResponse();
        }
        rateService.deleteRate(userId);
        return deleteRateResponse();
    }

    private ResponseEntity createRateResponse() {
        return ok(getResponse(
                messageRepository.CREATE_RATE_SUCCESS_CODE,
                messageRepository.CREATE_RATE_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity deleteRateResponse() {
        return ok(getResponse(
                messageRepository.DELETE_RATE_SUCCESS_CODE,
                messageRepository.DELETE_RATE_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity idMandatoryResponse() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponse(
                messageRepository.RATE_ID_MANDATORY_CODE,
                messageRepository.RATE_ID_MANDATORY
        ));
    }
}
