package com.github.crunc.spring;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class SimpleFluentTraceRequest extends SimpleFluentRequest<FluentTraceRequest> implements FluentTraceRequest {

    protected SimpleFluentTraceRequest(final RestOperations delegate, final String url) {
        super(delegate, HttpMethod.TRACE, url);
    }
}
