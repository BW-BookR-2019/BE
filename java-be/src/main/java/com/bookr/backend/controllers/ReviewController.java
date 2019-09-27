package com.bookr.backend.controllers;

import com.bookr.backend.models.Review;
import com.bookr.backend.models.ErrorDetail;
import com.bookr.backend.services.ReviewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController
{
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    ReviewService reviewService;


    @ApiOperation(value = "Create a review", responseContainer = "List")

    @PostMapping(value = "/review")
    public ResponseEntity<?> addNewReview(HttpServletRequest request, @Valid
                                                                      @RequestBody
                                                                            Review newReview) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newReview = reviewService.save(newReview);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newReviewURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{reviewid}").buildAndExpand(newReview.getReviewid()).toUri();
        responseHeaders.setLocation(newReviewURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a review")

    @DeleteMapping("/review/{id}")
    public ResponseEntity<?> deleteReviewById(HttpServletRequest request,
                                             @PathVariable
                                                     long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates a review", consumes = "Review", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Review Successfully Updated", response = void.class),
            @ApiResponse(code = 404, message = "Review Not Found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error Updating Book", response = ErrorDetail.class),
    })
    @PutMapping(value = "/Review/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateReview(@ApiParam(name = "Review Id", required = true) @PathVariable long id, @RequestBody @Valid Review review)
    {
        reviewService.update(review, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

