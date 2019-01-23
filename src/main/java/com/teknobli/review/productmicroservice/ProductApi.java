package com.teknobli.review.productmicroservice;

public class ProductApi {

    public static final String BASE_URL = "http://localhost:8080";
    public static final String UPDATE_RATING = "/product/updateRating/";


    public static String createUpdateRatingURL(String productId, Double rating) {
        return UPDATE_RATING + productId + "/" + rating;
    }
}
