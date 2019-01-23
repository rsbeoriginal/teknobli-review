package com.teknobli.review.merchantmicroservice;

public class EndPoints {

    public static final String BASE_URL = "https://teknobli-merchant.herokuapp.com/";

    public static final String UPDATE_RATING = "/merchant/updateRating/";


    public static String createUpdateRatingURL(String merchantId, Double rating) {
        return UPDATE_RATING + merchantId + "/" + rating;
    }
}
