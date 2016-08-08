package com.github.crunc.spring;


public interface FluentRestOperations {

    /**
     * Creates a new {@code GET} request for the given path or URL
     */
    FluentGetRequest get(String path);

    /**
     * Creates a new {@code POST} request for the given path or URL
     */
    FluentPostRequest post(String path);

    /**
     * Creates a new {@code HEAD} request for the given path or URL
     */
    FluentHeadRequest head(String path);

    /**
     * Creates a new {@code OPTIONS} request for the given path or URL
     */
    FluentOptionsRequest options(String path);

    /**
     * Creates a new {@code PUT} request for the given path or URL
     */
    FluentPutRequest put(String path);

    /**
     * Creates a new {@code PATCH} request for the given path or URL
     */
    FluentPatchRequest patch(String path);

    /**
     * Creates a new {@code DELETE} request for the given path or URL
     */
    FluentDeleteRequest delete(String path);

    /**
     * Creates a new {@code TRACE} request for the given path or URL
     */
    FluentTraceRequest trace(String path);

}
