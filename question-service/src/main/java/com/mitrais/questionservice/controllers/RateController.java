package com.mitrais.questionservice.controllers;

import javax.validation.Valid;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mitrais.questionservice.controllers.requests.RateRequest;
import com.mitrais.questionservice.exceptions.model.ServiceException;
import com.mitrais.questionservice.models.Rate;
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
    private RateService rateService;

    /**
     * Rate Controller Constructor
     *
     * @param rateService service of rate
     */
    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    /**
     * Create rate
     *
     * @param request type Rate
     * @return response entity object
     */
    @PostMapping("/")
    public ResponseEntity createRate(@Valid @RequestBody RateRequest request) {
        Rate rate = new Rate();
        BeanUtils.copyProperties(request, rate);
        rateService.createRate(rate, request.getPostId());
        return ok(getResponse(
                false,
                "00001",
                "A new rate has been created successfully",
                new ArrayList<>()
        ));
    }

    /**
     * delete rate
     *
     * @param userId of the rate
     * @return response entity
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteRate(@PathVariable String userId) {
        if (userId == null || userId.equalsIgnoreCase("0")) {
            throw new ServiceException("Rate ID should not be null or 0");
        }
        rateService.deleteRate(userId);
        return ok(getResponse(
                false,
                "00004",
                "Rate has been deleted successfully",
                new ArrayList<>()
        ));
    }
}
