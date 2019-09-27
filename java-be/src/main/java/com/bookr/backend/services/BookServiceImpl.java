package com.bookr.backend.services;

import com.bookr.backend.exceptions.ResourceNotFoundException;
import com.bookr.backend.models.Book;
import com.bookr.backend.models.Review;
import com.bookr.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "BookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepo;

    @Override //With pages
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookrepo.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }


    ////////////////////////////////////////////////////

    @Override //without pages
    public List<Book> findAll()
    {
        List<Book> list = new ArrayList<>();
        bookrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    ////////////////////////////////////////////////////

    @Override //Finding the book by Id and throwing an Exception not found if not
    public Book findBookById(long id)
    {
        return bookrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }


    ////////////////////////////////////////////////////

    @Override //delete by Id
    public void delete(long id)
    {
        if (bookrepo.findById(id).isPresent())
        {
            bookrepo.deleteById(id);
        }
        else
            throw new ResourceNotFoundException(Long.toString(id));
    }

    @Override
    public Book save(Book book)
    {
        Book newBook = new Book();

        if (book.getBooktitle() != null)
        {
            newBook.setBooktitle(book.getBooktitle());
        }
        if (book.getPublisher() != null)
        {
            newBook.setPublisher(book.getPublisher());
        }
        if (book.getAuthor() != null)
        {
            newBook.setAuthor(book.getAuthor());
        }

//        newBook.setCopy(book.getCopy());

        for (Review r : book.getReviews())
        {
            newBook.getReviews().add(r);
        }

        return bookrepo.save(newBook);
    }
}
//    @Override
//    public Book save(Book book)
//    {
//        Book stuff =new Book();
//        stuff.setBooktitle(book.getBooktitle());
//        stuff.setAuthor(book.getAuthor());
//        stuff.setPublisher(book.getPublisher());
//        return bookrepo.save(stuff);
//    }


