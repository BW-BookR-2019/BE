package com.bookr.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewid;

    @Column(nullable = false)
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"review", "hibernateLazyInitializer"})
    private User user;

    private int rating;

    public Review()
    {
    }

    public Review(String review, User user, int rating)
    {
        this.review = review;
        this.user = user;
        this.rating = rating;
    }

    public long getReviewid()
    {
        return reviewid;
    }

    public void setReviewid(long reviewid)
    {
        this.reviewid = reviewid;
    }

    public String getReview()
    {
        return review;
    }

    public void setReview(String review)
    {
        this.review = review;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }
}


