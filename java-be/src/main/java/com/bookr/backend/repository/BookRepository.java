package com.bookr.backend.repository;

import com.bookr.backend.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{
    List<Book> findAll();
}