package com.teknobli.review.merchantmicroservice;

public class EndPoints {

    public static final String BASE_URL = "http://localhost:8080";

    public static final String UPDATE_RATING = "/merchant/updateRating/";


    public static String createUpdateRatingURL(String merchantId, Double rating) {
        return UPDATE_RATING + merchantId + "/" + rating;
    }
}
