package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.dto.RateDto;
import com.mitrais.questionservice.models.Rate;
import com.mitrais.questionservice.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Rate Controller
 */
@RestController
@RequestMapping("/api")
public class RateController implements BaseController<Rate> {
    private RateService rateService;

    /**
     * Rate Controller Constructor
     * @param rateService service of rate
     */
    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    /**
     * Create rate
     * @param body type RateDto
     * @return response entity object
     */
    @PostMapping("/rate")
    public ResponseEntity createRate(@Valid @RequestBody RateDto body) {
        return rateService.createRate(body);
    }

    /**
     * delete rate
     * @param rateId of the rate
     * @return response entity
     */
    @DeleteMapping("/rate/{rateId}")
    public ResponseEntity deleteRate(@PathVariable String rateId) {
        return rateService.deleteRate(rateId);
    }
}
