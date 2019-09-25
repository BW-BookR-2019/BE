package com.bookr.backend.services;

import com.bookr.backend.exceptions.ResourceNotFoundException;
import com.bookr.backend.models.Review;
import com.bookr.backend.repository.ReviewRepository;
import com.bookr.backend.services.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Review update(Review review, long id)
    {
        Review uBook = reviewrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
        if (book.getTitle() != null)
        {
            uBook.setTitle(book.getTitle());
        }
        if (book.getISBN() != null)
        {
            uBook.setISBN(book.getISBN());
        }
        uBook.setCopy(book.getCopy());
        return reviewrepo.save(uBook);
    }
}
