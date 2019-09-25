package com.bookr.backend.services;

import com.bookr.backend.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService
{
    List<Book> findAll(Pageable pageable);

    List<Book> findAll();

    Book findBookById(long id);

    void delete(long id);

    Book save(Book book);


}