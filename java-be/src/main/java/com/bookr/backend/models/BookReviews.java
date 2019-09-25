package com.bookr.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bookreviews")
public class BookReviews extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties("bookreviews")
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "reviewid")
    @JsonIgnoreProperties("bookreviews")
    private Review review;

    public BookReviews()
    {
    }

    public BookReviews(Book book, Review review)
    {
        this.book = book;
        this.review = review;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public Review getReview()
    {
        return review;
    }

    public void setReview(Review review)
    {
        this.review = review;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof BookReviews))
        {
            return false;
        }
        BookReviews bookReviews = (BookReviews) o;
        return getBook().equals(bookReviews.getBook()) && getReview().equals(bookReviews.getReview());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getBook(), getReview());
    }

    @Override
    public String toString()
    {
        return "BookReviews{" + "book=" + book.getBookid() + ", review=" + review.getReviewid() + '}';
    }
}

