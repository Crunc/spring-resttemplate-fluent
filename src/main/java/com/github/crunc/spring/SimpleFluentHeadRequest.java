package com.github.crunc.spring;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class SimpleFluentHeadRequest extends SimpleFluentRequest<FluentHeadRequest> implements FluentHeadRequest {

    protected SimpleFluentHeadRequest(final RestOperations delegate, final String url) {
        super(delegate, HttpMethod.HEAD, url);
    }
}
