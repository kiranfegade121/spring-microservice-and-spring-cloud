package com.training.apigateway.util;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class FilterUtil {
	
	public static final String MOVIE_CATALOG_SERVICE_NAME = "movie-catalog-service";
	
	public static final String MOVIE_CATALOG_SERVICE_AUTH_TOKEN_KEY = "token";
	
	public static final String MOVIE_CATALOG_SERVICE_AUTH_TOKEN_VALUE = "dsabhbfhjsbfbnsfdaskkwkjfjrnjknklefennknknknk";
	
	public static final int AUTHENTICATION_FILTER_ORDER = FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
	
	public static final int RESPONSE_AUTDIT_FILTER_ORDER = AUTHENTICATION_FILTER_ORDER + 1;
			
}
