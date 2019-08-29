package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.controllers.requests.RateRequest;
import com.mitrais.questionservice.dto.RateDto;
import com.mitrais.questionservice.exceptions.model.ServiceException;
import com.mitrais.questionservice.models.Rate;
import com.mitrais.questionservice.services.RateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Rate Controller
 */
@RestController
@RequestMapping("/api")
public class RateController extends BaseController<RateDto> {
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
    public ResponseEntity createRate(@Valid @RequestBody RateRequest body) {
        RateDto dto = new RateDto();
        BeanUtils.copyProperties(body, dto);
        rateService.createRate(dto, body.getPostId());
        return ok(getResponse(
                false,
                "00001",
                "A new rate has been created successfully",
                new ArrayList<>()
        ));
    }

    /**
     * delete rate
     * @param userId of the rate
     * @return response entity
     */
    @DeleteMapping("/rate/{rateId}")
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
