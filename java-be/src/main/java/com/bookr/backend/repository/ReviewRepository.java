package com.bookr.backend.repository;

import com.bookr.backend.models.Review;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>
{

}
