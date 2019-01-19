package com.teknobli.review.services.implementation;

import com.teknobli.review.dto.ProductRatingDTO;
import com.teknobli.review.entity.ProductRatingEntity;
import com.teknobli.review.repository.ProductRatingRepository;
import com.teknobli.review.services.ProductRatingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRatingImpl implements ProductRatingService {

    @Autowired
    ProductRatingRepository productRatingRepository;

    @Override
    public ProductRatingDTO add(ProductRatingDTO productRatingDTO) {
        ProductRatingEntity productRatingEntity= new ProductRatingEntity();
        BeanUtils.copyProperties(productRatingDTO,productRatingEntity);
        ProductRatingDTO productRatingDTODb = new ProductRatingDTO();
        BeanUtils.copyProperties(productRatingEntity,productRatingDTODb);
        return productRatingDTODb;
    }

    @Override
    public Double getRating(String productId) {
        return productRatingRepository.getRating(productId);
    }

    @Override
    public ProductRatingDTO select(String productId, String userId, String orderId) {
        ProductRatingDTO productRatingDTO = new ProductRatingDTO();
        BeanUtils.copyProperties(productRatingRepository.findByProductIdAndUserIdAndOrderId(productId,userId,orderId),productRatingDTO);
        return productRatingDTO;
    }
}
