package com.teknobli.review.repository;

import com.teknobli.review.dto.ProductRatingDTO;
import com.teknobli.review.entity.ProductRatingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRatingRepository extends CrudRepository<ProductRatingEntity,String> {
    @Query(value = "FROM ProductRatingEntity P WHERE (productId = ?1 AND  userId = ?2 AND orderId = ?3) ")
    ProductRatingEntity findByProductIdAndUserIdAndOrderId(String productId,String userId,String orderId);

    @Query(value = "SELECT AVG(rating) FROM ProductRatingEntity WHERE productId = ?1")
    Double getRating(String productId);

    @Query("SELECT rating FROM ProductRatingEntity WHERE (orderId = ?1 AND productId =?2 AND userId =?3)")
    Double getUserProductRating(String orderId,String productId,String userId);
}
