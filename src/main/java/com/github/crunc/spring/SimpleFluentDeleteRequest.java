package com.github.crunc.spring;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class SimpleFluentDeleteRequest extends SimpleFluentRequest<FluentDeleteRequest> implements FluentDeleteRequest {

    protected SimpleFluentDeleteRequest(final RestOperations delegate, final String url) {
        super(delegate, HttpMethod.DELETE, url);
    }
}
