package com.github.crunc.spring;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

/**
 * Created by haukej on 05/08/16.
 */
public class SimpleFluentPatchRequest extends SimpleFluentEntityRequest<FluentPatchRequest> implements FluentPatchRequest {

    public SimpleFluentPatchRequest(final RestOperations delegate, final String url) {
        super(delegate, HttpMethod.PATCH, url);
    }
}
