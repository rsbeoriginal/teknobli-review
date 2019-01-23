package com.teknobli.review.services;

import com.teknobli.review.dto.ProductRatingDTO;
import com.teknobli.review.entity.ProductRatingEntity;

import java.util.List;

public interface ProductRatingService {

    ProductRatingDTO add(ProductRatingDTO productRatingDTO);

    Double getRating(String productId);

    ProductRatingDTO select(String productId,String userId,String orderId);

    Double getUserRating(ProductRatingDTO productRatingDTO);

    List<ProductRatingEntity> getReviews(String productId);
}
