package com.github.crunc.spring;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

/**
 * Created by haukej on 05/08/16.
 */
public class SimpleFluentPutRequest extends SimpleFluentEntityRequest<FluentPutRequest> implements FluentPutRequest {

    public SimpleFluentPutRequest(final RestOperations delegate, final String url) {
        super(delegate, HttpMethod.PUT, url);
    }
}
