package com.github.crunc.spring;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Base interface for requests which can be configured and executed in a fluent way
 */
public interface FluentRequest<T extends FluentRequest<T>> {

    /**
     * Sets a url or query parameter
     */
    T param(String name, Object value);

    /**
     * Sets the {@code Accept} header
     */
    T accept(MediaType type);

    /**
     * Sets the {@code Accept} header
     */
    T accept(List<MediaType> types);

    /**
     * Sets the {@code Accept-Charset} header
     */
    T acceptCharset(Charset charset);

    /**
     * Sets the {@code Accept-Charset} header
     */
    T acceptCharset(List<Charset> charsets);

    /**
     * Sets the {@code Accept-Encoding} header
     */
    T acceptEncoding(String encoding);

    /**
     * Sets the {@code Accept-Encoding} header
     */
    T acceptEncoding(List<String> encodings);

    /**
     * Sets the {@code Accept-Language} header
     */
    T acceptLanguage(String language);

    /**
     * Sets the {@code Accept-Language} header
     */
    T acceptLanguage(List<String> languages);

    /**
     * Sets the {@code Authorization} header
     */
    T authorization(String authorization);

    /**
     * Sets the {@code Authorization} header to {@code Basic base64(username:credentials)}
     */
    T authorizationBasic(String username, String credentials);

    /**
     * Sets the {@code Cache-Control} header
     */
    T cacheControl(String cacheControl);

    /**
     * Sets the {@code Connection} header
     */
    T connection(String connection);

    /**
     * Sets the {@code Cookie} header
     */
    T cookie(String cookie);

    /**
     * Sets the {@code Date} header
     */
    T date(String date);

    /**
     * Sets the {@code Date} header
     */
    T date(Date date);

    /**
     * Sets the given value for the header or adds it to the list of values
     */
    T header(String name, String value);

    /**
     * Sets the given values for the header or adds them to the list of values
     */
    T header(String name, List<String> values);

    /**
     * Adds the given headers to the request
     */
    T headers(Map<String, List<String>> headers);

    /**
     * Executes the request and returns the response with it's content converted to the given type
     */
    <E> ResponseEntity<E> forEntity(Class<E> responseType);

    /**
     * Executes the request and returns the response
     */
    ResponseEntity<Object> forEntity();

    /**
     * Executes the request and returns the response with it's content converted to a list of the given type
     */
    <E> ResponseEntity<List<E>> forListEntity(Class<E> responseType);

    /**
     * Executes the request and returns the response body converted to the given type
     */
    <O> O forObject(Class<O> responseType);

    /**
     * Executes the request and returns the response body
     */
    Object forObject();

    /**
     * Executes the request and returns the response body converted to a list of the given type
     */
    <O> List<O> forList(Class<O> responseType);
}
