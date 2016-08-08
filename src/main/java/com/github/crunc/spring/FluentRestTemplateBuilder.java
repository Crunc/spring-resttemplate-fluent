package com.github.crunc.spring;

import org.springframework.web.client.RestOperations;

/**
 * Fluent builder for creating an instance of {@link FluentRestTemplate}
 */
public class FluentRestTemplateBuilder {

    private String protocol;
    private String host;
    private Integer port;
    private String path;

    public FluentRestTemplateBuilder protocol(final String protocol) {
        this.protocol = protocol;
        return this;
    }

    public FluentRestTemplateBuilder http() {
        return protocol("http");
    }

    public FluentRestTemplateBuilder https() {
        return protocol("https");
    }

    public FluentRestTemplateBuilder host(final String host) {
        this.host = host;
        return this;
    }

    public FluentRestTemplateBuilder localhost() {
        return host("localhost");
    }

    public FluentRestTemplateBuilder port(final Integer port) {
        this.port = port;
        return this;
    }

    public FluentRestTemplateBuilder path(final String path) {
        this.path = path;
        return this;
    }

    public FluentRestTemplate using(final RestOperations restOperations) {
        return new FluentRestTemplate(restOperations, baseUrl());
    }

    private String baseUrl() {

        final StringBuilder url = new StringBuilder();

        if (protocol != null) {
            url.append(protocol);
        } else {
            url.append("http");
        }

        url.append("://");

        if (host != null) {
            url.append(host);
        } else {
            url.append("localhost");
        }

        if (port != null) {
            url.append(":").append(port);
        }

        if (path != null) {
            if (!path.startsWith("/")) {
                url.append("/");
            }
            url.append(path);
        }

        return url.toString();
    }
}
