package com.training.apigateway.filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.training.apigateway.util.FilterUtil;

@Component
public class AuthorizationFilter extends ZuulFilter {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		logger.info("Entered into Authorization Filter >>> " + context.getRequest().getRequestURI());
		Object serviceId = context.get("serviceId");
		logger.info("Service ID >>>>" + serviceId);
		if (serviceId != null && FilterUtil.MOVIE_CATALOG_SERVICE_NAME.equals(serviceId.toString())) {
			String token = context.getRequest().getParameter(FilterUtil.MOVIE_CATALOG_SERVICE_AUTH_TOKEN_KEY);
			if (!FilterUtil.MOVIE_CATALOG_SERVICE_AUTH_TOKEN_VALUE.equals(token))  {
				context.setResponseStatusCode(401);
				context.setResponseBody("<h4>You are not authorized....</h4>");
				context.getResponse().setContentType("text/html");				
			}			
		}
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterUtil.AUTHENTICATION_FILTER_ORDER;
	}

}
