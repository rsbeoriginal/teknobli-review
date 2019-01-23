package com.teknobli.review.controller;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.teknobli.review.dto.ProductRatingDTO;
import com.teknobli.review.entity.ProductRatingEntity;
import com.teknobli.review.services.ProductRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductRatingController {

    @Autowired
    ProductRatingService productRatingService;

    @PostMapping("/add")
    public ProductRatingDTO add(@RequestBody ProductRatingDTO productRatingDTO){
        ProductRatingDTO res= new ProductRatingDTO();
        if(decodeToken(productRatingDTO.getIdToken()).equals(productRatingDTO.getUserId())) {
            return productRatingService.add(productRatingDTO);
        }else{
            res.setIdToken("Authentication failure");
        }
        return res;
    }

    @GetMapping("/select/{productId}/{userId}/{orderId}")
    public ProductRatingDTO select(@PathVariable("productId") String productId,@PathVariable("userId") String userId,@PathVariable("orderId")String orderId){
        return productRatingService.select(productId,userId,orderId);
    }

    @GetMapping("/getRating/{productId}")
    public Double getRating(@PathVariable("productId") String productId){
        return productRatingService.getRating(productId);
    }

    @PostMapping("/getUserRating")
    public Double getUserRating(@RequestBody ProductRatingDTO productRatingDTO){
        return productRatingService.getUserRating(productRatingDTO);
    }

    @GetMapping("/getReviews/{productId}")
    public List<ProductRatingEntity> getReviews(@PathVariable("productId")String productId){
        return productRatingService.getReviews(productId);
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
