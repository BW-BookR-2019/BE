package com.bookr.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;
    private String booktitle;
    private String author;
    private String publisher;

    @ManyToMany
    @JoinTable(name = "bookreviews",
               joinColumns = {@JoinColumn(name = "bookid")},
               inverseJoinColumns = {@JoinColumn(name = "reviewid")})
    @JsonIgnoreProperties("books")
    private List<Review> reviews = new ArrayList<>();

    public Book()
    {
    }

    public Book(String booktitle, String author, String publisher, List<Review> reviews)
    {
        this.booktitle = booktitle;
        this.author = author;
        this.publisher = publisher;
        this.reviews = reviews;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public List<Review> getReviews()
    {
        return reviews;
    }

    public void setReviews(List<Review> reviews)
    {
        this.reviews = reviews;
    }

}
