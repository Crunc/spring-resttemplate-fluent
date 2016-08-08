package com.github.crunc.spring;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public abstract class SimpleFluentRequest<T extends FluentRequest<T>> extends FluentRequestBase<T> {

    protected SimpleFluentRequest(final RestOperations delegate, final HttpMethod method, final String url) {
        super(delegate, method, url);
    }

    @Override
    protected HttpEntity<?> createRequestEntity(final HttpHeaders headers) {
        return new HttpEntity<>(null, headers);
    }
}
