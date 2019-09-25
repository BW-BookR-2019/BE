package com.bookr.backend.repository;

import com.bookr.backend.models.Review;
import com.bookr.backend.models.Book;
import com.bookr.backend.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long>
{
    @Query(value = "SELECT COUNT(*) as count FROM bookreviews WHERE bookid = :bookid AND reviewid = :reviewid",
            nativeQuery = true)
    JustTheCount checkBookReviewsCombo(long bookid, long reviewid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM BookReviews WHERE bookid = :bookid AND reviewid = :reviewid")
    void deleteBookReviews(long bookid, long reviewid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO BookReviews(bookid, reviewid) VALUES (:bookid, :reviewid)",
            nativeQuery = true)
    void insertBookReviews(long bookid, long reviewid);

    Review findReviewByUser(String User);
}

