package com.github.crunc.spring;

import org.springframework.http.MediaType;

public interface FluentEntityRequest<T extends FluentEntityRequest<T>> extends FluentRequest<T> {

    /**
     * Sets the {@code Content-Length} header
     */
    T contentLength(Long contentLength);

    /**
     * Sets the {@code Content-Type} header
     */
    T contentType(MediaType contentType);

    /**
     * Sets the body (content) of the request
     */
    T body(Object body);

}
