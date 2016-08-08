package com.github.crunc.spring;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestOperations;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class FluentRequestBase<T extends FluentRequest<T>> implements FluentRequest<T> {

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final SimpleDateFormat RFC_7231_FORMAT;

    static {
        RFC_7231_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        RFC_7231_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    protected final RestOperations delegate;

    protected final String url;
    protected final HttpMethod method;

    protected final Map<String, Object> params;
    protected final HttpHeaders headers;

    protected FluentRequestBase(final RestOperations delegate, final HttpMethod method, final String url, final Map<String, Object> params, final HttpHeaders headers) {
        this.method = method;
        this.url = url;

        Assert.notNull(delegate, "delegate RestOperations must not be null");

        this.delegate = delegate;
        this.params = params != null ? params : new HashMap<String, Object>();
        this.headers = headers != null ? headers : new HttpHeaders();
    }

    protected FluentRequestBase(final RestOperations delegate, final HttpMethod method, final String url) {
        this(delegate, method, url, null, null);
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    @Override
    public T param(final String name, final Object value) {
        params.put(name, value);
        return self();
    }

    @Override
    public T accept(final MediaType type) {
        if (type != null) {
            header(HttpHeaders.ACCEPT, type.toString());
        }
        return self();
    }

    @Override
    public T accept(final List<MediaType> types) {
        if (types != null) {
            for (final MediaType type: types) {
                accept(type);
            }
        }
        return self();
    }

    @Override
    public T acceptCharset(final Charset charset) {
        if (charset != null) {
            headers.setAcceptCharset(Collections.singletonList(charset));
        }
        return self();
    }

    @Override
    public T acceptCharset(final List<Charset> charsets) {
        if (charsets != null) {
            headers.setAcceptCharset(charsets);
        }
        return self();
    }

    @Override
    public T acceptEncoding(final String encoding) {
        return header(HttpHeaders.ACCEPT_ENCODING, encoding);
    }

    @Override
    public T acceptEncoding(final List<String> encodings) {
        return header(HttpHeaders.ACCEPT_ENCODING, encodings);
    }

    @Override
    public T acceptLanguage(final String language) {
        return header(HttpHeaders.ACCEPT_LANGUAGE, language);
    }

    @Override
    public T acceptLanguage(final List<String> languages) {
        return header(HttpHeaders.ACCEPT_LANGUAGE, languages);
    }

    @Override
    public T authorization(final String authorization) {
        return header(HttpHeaders.AUTHORIZATION, authorization);
    }

    @Override
    public T authorizationBasic(final String username, final String credentials) {
        return authorization(basicAuth(username, credentials));
    }

    private String basicAuth(final String username, final String credentials) {
        return Base64Utils.encodeToString(new StringBuilder("Basic ")
                .append(username != null ? username : "")
                .append(":")
                .append(credentials != null ? credentials : "")
                .toString()
                .getBytes(UTF_8));
    }

    @Override
    public T cacheControl(final String cacheControl) {
        return header(HttpHeaders.CACHE_CONTROL, cacheControl);
    }

    @Override
    public T connection(final String connection) {
        return header(HttpHeaders.CONNECTION, connection);
    }

    @Override
    public T cookie(final String cookie) {
        return header(HttpHeaders.COOKIE, cookie);
    }

    @Override
    public T date(final String date) {
        return header(HttpHeaders.DATE, date);
    }

    @Override
    public T date(final Date date) {
        if (date != null) {
            return date(RFC_7231_FORMAT.format(date));
        } else {
            return date((String) null);
        }
    }

    @Override
    public T header(final String name, final String value) {
        headers.add(name, value);
        return self();
    }

    @Override
    public T header(final String name, final List<String> values) {
        if (values != null) {
            for (final String value: values) {
                header(name, value);
            }
        }
        return self();
    }

    @Override
    public T headers(final Map<String, List<String>> headers) {
        if (headers != null) {
            for (final Map.Entry<String, List<String>> header: headers.entrySet()){
                header(header.getKey(), header.getValue());
            }
        }
        return self();
    }


    @Override
    public <E> ResponseEntity<E> forEntity(final Class<E> responseType) {
        return delegate.exchange(url, method, createRequestEntity(headers), responseType, params);
    }

    @Override
    public ResponseEntity<Object> forEntity() {
        return forEntity(Object.class);
    }

    @Override
    public <E> ResponseEntity<List<E>> forListEntity(final Class<E> responseType) {
        return delegate.exchange(url, method, createRequestEntity(headers), new ParameterizedTypeReference<List<E>>() {
        }, params);
    }

    @Override
    public <O> O forObject(final Class<O> responseType) {
        return forEntity(responseType).getBody();
    }

    @Override
    public Object forObject() {
        return forObject(Object.class);
    }

    @Override
    public <O> List<O> forList(final Class<O> responseType) {
        return forListEntity(responseType).getBody();
    }

    private Class<?> arrayTypeOf(final Class<?> type) {
        try {
            return Class.forName("[L" + type.getName() + ";");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("can not determine array type for <" + type + ">");
        }
    }

    protected abstract HttpEntity<?> createRequestEntity(HttpHeaders headers);
}
