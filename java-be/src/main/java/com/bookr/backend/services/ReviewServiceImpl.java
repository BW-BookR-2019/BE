package com.bookr.backend.services;

import com.bookr.backend.exceptions.ResourceNotFoundException;
import com.bookr.backend.models.Review;
import com.bookr.backend.repository.ReviewRepository;
import com.bookr.backend.services.ReviewService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value="ReviewService")
public class ReviewServiceImpl implements ReviewService
{
    @Autowired
    private ReviewRepository reviewrepo;

    @Override
    public Review findReviewById(long id)
    {
        return reviewrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (reviewrepo.findById(id).isPresent())
        {
            reviewrepo.deleteById(id);
        }
        else
            throw new ResourceNotFoundException(Long.toString(id));
    }

    @Override
    public Review update(Review review, long id) {
        return null;
    }

    @Transactional
    @Override
    public Review save(Review review)
    {
        return reviewrepo.save(review);
    }

//    @Override
//    public List<Review> findByUserName(String username)
//    {
//        List<Review> list = new ArrayList<>();
//        reviewrepo.findAll().iterator().forEachRemaining(list::add);
//
//        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
//        return list;
//    }

//    @Override
//    public Review update(Review review, long id)
//    {
//        Review newReview = reviewrepo.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
//
//        if (review.getReview() != null)
//        {
//            newReview.setReview(review.getReview());
//        }
//
//        if (Review.getUser() != null)
//        {
//            newReview.setUser(review.getUser());
//        }
//
//        return reviewrepo.save(newReview);
//    }
}

