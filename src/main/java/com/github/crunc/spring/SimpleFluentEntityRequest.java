package com.github.crunc.spring;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

public abstract class SimpleFluentEntityRequest<T extends FluentEntityRequest<T>> extends FluentRequestBase<T> implements FluentEntityRequest<T> {

    private Object body;

    protected SimpleFluentEntityRequest(final RestOperations delegate, HttpMethod method, final String url) {
        super(delegate, method, url);
    }

    @Override
    public T contentLength(final Long contentLength) {
        if (contentLength != null) {
            headers.setContentLength(contentLength);
        }
        return self();
    }

    @Override
    public T contentType(final MediaType contentType) {
        if (contentType != null) {
            headers.setContentType(contentType);
        }
        return self();
    }

    @Override
    public T body(final Object body) {
        this.body = body;
        return self();
    }

    @Override
    protected HttpEntity<?> createRequestEntity(final HttpHeaders headers) {
        return new HttpEntity<>(body, headers);
    }
}
