package com.bookr.backend.services;

import com.bookr.backend.exceptions.ResourceNotFoundException;
import com.bookr.backend.models.Review;
import com.bookr.backend.repository.ReviewRepository;
import com.bookr.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(value="reviewService")
public class ReviewServiceImpl implements ReviewService
{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void delete(long id)
    {
        if (reviewRepository.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (reviewRepository.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                reviewRepository.deleteById(id);
            }
            else
            {
                throw new ResourceNotFoundException(Long.toString(id) + " " + authentication.getName());
            }
        }
        else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Review update(Review review, long id)
    {
        Review newReview = reviewRepository.findById(id)
                                           .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (review.getReview() != null)
        {
            newReview.setReview(review.getReview());
        }

        if (review.getUser() != null)
        {
            newReview.setUser(review.getUser());
        }

        return reviewRepository.save(newReview);
    }


    @Transactional
    @Override
    public Review save(Review Review)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

              Review.setUser(userRepository.findByUsername(authentication.getName()));
        //User currentUser = userRepository.findByUsername(authentication.getName());
        Review saveReview =  reviewRepository.save(Review);
        return saveReview;
    }


}