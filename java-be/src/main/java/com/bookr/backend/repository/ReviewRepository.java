package com.bookr.backend.repository;

import com.bookr.backend.models.Review;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long>
{

}
