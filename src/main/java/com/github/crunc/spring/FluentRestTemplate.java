package com.github.crunc.spring;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

/**
 *
 */
public class FluentRestTemplate implements FluentRestOperations {

    private final RestOperations delegate;
    private final String baseUrl;

    public FluentRestTemplate(final RestOperations delegate, final String baseUrl) {
        this.delegate = delegate;
        this.baseUrl = baseUrl;
    }

    public FluentRestTemplate(final RestOperations delegate) {
        this(delegate, null);
    }

    @Override
    public FluentGetRequest get(final String path) {
        return new SimpleFluentGetRequest(delegate, url(path));
    }

    @Override
    public FluentPostRequest post(final String path) {
        return new SimpleFluentPostRequest(delegate, url(path));
    }

    @Override
    public FluentHeadRequest head(final String path) {
        return new SimpleFluentHeadRequest(delegate, url(path));
    }

    @Override
    public FluentOptionsRequest options(final String path) {
        return new SimpleFluentOptionsRequest(delegate, url(path));
    }

    @Override
    public FluentPutRequest put(final String path) {
        return new SimpleFluentPutRequest(delegate, url(path));
    }

    @Override
    public FluentPatchRequest patch(final String path) {
        return new SimpleFluentPatchRequest(delegate, url(path));
    }

    @Override
    public FluentDeleteRequest delete(final String path) {
        return new SimpleFluentDeleteRequest(delegate, url(path));
    }

    @Override
    public FluentTraceRequest trace(final String path) {
        return new SimpleFluentTraceRequest(delegate, url(path));
    }

    private String url(final String path) {

        if (path == null) {
            return baseUrl;
        } else if (path.startsWith("http://")) {
            return path;
        } else if (path.startsWith("https://")) {
            return path;
        } else {
            return join(baseUrl, path);
        }
    }

    private String join(final String baseUrl, final String path) {
        if (baseUrl != null) {
            if (path == null) {
                return baseUrl;
            } else if (baseUrl.endsWith("/")) {
                if (path.startsWith("/")) {
                    return baseUrl.concat(path.substring(1));
                } else {
                    return baseUrl.concat(path);
                }
            } else {
                if (path.startsWith("/")) {
                    return baseUrl.concat(path);
                } else {
                    return baseUrl.concat("/").concat(path);
                }
            }
        } else {
            return path;
        }
    }

    public static FluentRestTemplateBuilder builder() {
        return new FluentRestTemplateBuilder();
    }
}
