package com.teknobli.review.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.teknobli.review.dto.MerchantRatingDTO;
import com.teknobli.review.services.MerchantRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
@CrossOrigin
public class MerchantRatingController {

    @Autowired
    MerchantRatingService merchantRatingService;

    @PostMapping("/add")
    public MerchantRatingDTO add(@RequestBody MerchantRatingDTO merchantRatingDTO){
        MerchantRatingDTO res = new MerchantRatingDTO();
        if(decodeToken(merchantRatingDTO.getIdToken()).equals(merchantRatingDTO.getUserId())){
            return merchantRatingService.add(merchantRatingDTO);
        }else{
            res.setIdToken("Authentication failure");
        }
        return res;
    }

    @GetMapping("select/{merchantId}/{userId}/{orderId}")
    public MerchantRatingDTO select(@PathVariable("merchantId") String merchantId, @PathVariable("userId") String userId, @PathVariable("orderId")String orderId){
        return merchantRatingService.select(merchantId,userId,orderId);
    }

    @GetMapping("getRating/{merchantId}")
    public Double getRating(@PathVariable("merchantId") String merchantId){
        return merchantRatingService.getRating(merchantId);
    }

    @PostMapping("/getUserRating")
    public Double getUserRating(@RequestBody MerchantRatingDTO merchantRatingDTO){
        return merchantRatingService.getUserRating(merchantRatingDTO);
    }

    private String decodeToken(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return decodedToken.getUid();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        return null;
    }
}
