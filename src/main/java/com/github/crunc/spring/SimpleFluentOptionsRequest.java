package com.github.crunc.spring;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class SimpleFluentOptionsRequest extends SimpleFluentRequest<FluentOptionsRequest> implements FluentOptionsRequest {

    protected SimpleFluentOptionsRequest(final RestOperations delegate, final String url) {
        super(delegate, HttpMethod.OPTIONS, url);
    }
}
