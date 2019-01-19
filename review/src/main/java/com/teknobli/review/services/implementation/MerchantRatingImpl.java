package com.teknobli.review.services.implementation;

import com.teknobli.review.dto.MerchantRatingDTO;
import com.teknobli.review.entity.MerchantRatingEntity;
import com.teknobli.review.repository.MerchantRatingRepository;
import com.teknobli.review.services.MerchantRatingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
public class MerchantRatingImpl implements MerchantRatingService {

    @Autowired
    MerchantRatingRepository merchantRatingRepository;

    @Override
    @Transactional(readOnly = false)
    public MerchantRatingDTO add(MerchantRatingDTO merchantRatingDTO) {
        MerchantRatingEntity merchantRatingEntity = new MerchantRatingEntity();
        BeanUtils.copyProperties(merchantRatingDTO,merchantRatingEntity);
        MerchantRatingEntity merchantRatingEntityDb=merchantRatingRepository.save(merchantRatingEntity);
        MerchantRatingDTO merchantRatingDTODb = new MerchantRatingDTO();
        BeanUtils.copyProperties(merchantRatingEntityDb, merchantRatingDTODb);
        return merchantRatingDTODb;
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
