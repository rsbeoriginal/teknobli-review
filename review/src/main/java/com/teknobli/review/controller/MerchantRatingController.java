package com.teknobli.review.controller;

import com.teknobli.review.dto.MerchantRatingDTO;
import com.teknobli.review.services.MerchantRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
public class MerchantRatingController {

    @Autowired
    MerchantRatingService merchantRatingService;

    @PostMapping("/add")
    public MerchantRatingDTO add(@RequestBody MerchantRatingDTO merchantRatingDTO){
        return merchantRatingService.add(merchantRatingDTO);
    }

    @GetMapping("select/{merchantId}/{userId}/{orderId}")
    public MerchantRatingDTO select(@PathVariable("merchantId") String merchantId, @PathVariable("userId") String userId, @PathVariable("orderId")String orderId){
        return merchantRatingService.select(merchantId,userId,orderId);
    }

    @GetMapping("getRating/{merchantId}")
    public Double getRating(@PathVariable("merchantId") String merchantId){
        return merchantRatingService.getRating(merchantId);
    }
}
