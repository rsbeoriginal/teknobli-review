package com.teknobli.review.services.implementation;

import com.teknobli.review.dto.MerchantRatingDTO;
import com.teknobli.review.entity.MerchantRatingEntity;
import com.teknobli.review.merchantmicroservice.EndPoints;
import com.teknobli.review.repository.MerchantRatingRepository;
import com.teknobli.review.services.MerchantRatingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
public class MerchantRatingImpl implements MerchantRatingService {

    @Autowired
    MerchantRatingRepository merchantRatingRepository;

    @Override
    @Transactional(readOnly = false)
    public MerchantRatingDTO add(MerchantRatingDTO merchantRatingDTO) {

        System.out.println(merchantRatingDTO);
        MerchantRatingEntity merchantRatingEntity = new MerchantRatingEntity();
        BeanUtils.copyProperties(merchantRatingDTO,merchantRatingEntity);
        MerchantRatingEntity merchantRatingEntityDb=merchantRatingRepository.save(merchantRatingEntity);
        MerchantRatingDTO merchantRatingDTODb = new MerchantRatingDTO();
        BeanUtils.copyProperties(merchantRatingEntityDb, merchantRatingDTODb);

        //send rating to merchant microservice
        sendRatingToMerchantService(merchantRatingDTODb.getMerchantId(),getRating(merchantRatingDTODb.getMerchantId()));

        return merchantRatingDTODb;
    }

    private void sendRatingToMerchantService(String merchantId,Double newRating) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = EndPoints.BASE_URL + EndPoints.createUpdateRatingURL(merchantId,newRating);
        System.out.println(URL);
        restTemplate.put(URL, String.class);
    }

    @Override
    public Double getRating(String merchantId) {
        return merchantRatingRepository.getRating(merchantId);
    }

    @Override
    public MerchantRatingDTO select(String merchantId, String userId, String orderId) {

        MerchantRatingDTO merchantRatingDTO= new MerchantRatingDTO();
        BeanUtils.copyProperties(merchantRatingRepository.findByMerchantIdAndUserIdAndOrderId(merchantId,userId,orderId),merchantRatingDTO);
        return merchantRatingDTO;
    }
}
