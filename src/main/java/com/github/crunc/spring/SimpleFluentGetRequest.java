package com.github.crunc.spring;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class SimpleFluentGetRequest extends SimpleFluentRequest<FluentGetRequest> implements FluentGetRequest {

    protected SimpleFluentGetRequest(final RestOperations delegate, final String url) {
        super(delegate, HttpMethod.GET, url);
    }
}
