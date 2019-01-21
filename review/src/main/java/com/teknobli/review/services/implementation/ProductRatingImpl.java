package com.teknobli.review.services.implementation;

import com.teknobli.review.dto.ProductRatingDTO;

import com.teknobli.review.entity.ProductRatingEntity;
import com.teknobli.review.repository.ProductRatingRepository;
import com.teknobli.review.services.ProductRatingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
public class ProductRatingImpl implements ProductRatingService {

    @Autowired
    ProductRatingRepository productRatingRepository;

    @Override
    @Transactional(readOnly = false)
    public ProductRatingDTO add(ProductRatingDTO productRatingDTO) {
        ProductRatingEntity productRatingEntity= new ProductRatingEntity();
        BeanUtils.copyProperties(productRatingDTO,productRatingEntity);
        ProductRatingDTO productRatingDTODb = new ProductRatingDTO();
        BeanUtils.copyProperties(productRatingRepository.save(productRatingEntity),productRatingDTODb);
        BeanUtils.copyProperties(productRatingEntity,productRatingDTODb);

        sendRatingToProductService(productRatingDTODb.getProductId(),getRating(productRatingDTODb.getProductId()));

        return productRatingDTODb;
    }

    private void sendRatingToProductService(String productId, Double newRating) {
        RestTemplate restTemplate = new RestTemplate();
//        String URL = ProductApi.BASE_URL + EndPoints.createUpdateRatingURL(productId,newRating);
        String URL = "http://172.16.20.78:8000/product/updaterating/"+productId+"/"+newRating;
        System.out.println(URL);
        restTemplate.put(URL, String.class);
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
