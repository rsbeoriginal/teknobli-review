package com.teknobli.review.repository;


import com.teknobli.review.entity.MerchantRatingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRatingRepository extends CrudRepository<MerchantRatingEntity,String> {

    @Query(value = "FROM MerchantRatingEntity WHERE (merchantId = ?1 AND  userId = ?2 AND orderId = ?3) ")
    MerchantRatingEntity findByMerchantIdAndUserIdAndOrderId(String merchantId,String userId,String orderId);

    @Query(value = "SELECT AVG(rating) FROM MerchantRatingEntity WHERE merchantId = ?1")
    double getRating(String merchantId);

    @Query("SELECT rating FROM MerchantRatingEntity WHERE (orderId = ?1 AND merchantId =?2 AND userId =?3)")
    Double getUserMerchantRating(String orderId, String merchantId, String userId);
}
