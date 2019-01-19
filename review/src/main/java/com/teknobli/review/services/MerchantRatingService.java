package com.teknobli.review.services;

import com.teknobli.review.dto.MerchantRatingDTO;


public interface MerchantRatingService {

    MerchantRatingDTO add(MerchantRatingDTO merchantRatingDTO);

    Double getRating(String merchantId);

    MerchantRatingDTO select(String merchantId,String userId,String orderId);
}
