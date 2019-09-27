package com.bookr.backend.repository;


import com.bookr.backend.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long>
{

}
