package com.github.crunc.spring;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Unit test for setting HTTP headers with {@link FluentRestTemplate}
 */
public class AcceptHeaderTest extends MockServerTest {

    @Test
    public void acceptSingleValue() {

        // given
        final FluentRestTemplate rest = FluentRestTemplate.builder()
                .http()
                .host(host())
                .port(port())
                .using(new RestTemplate());

        when(request()
                .withMethod("GET")
                .withPath("/test/42")
                .withHeader("Accept", "application/json"))
                .respond(response()
                        .withStatusCode(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}"));

        // when
        final ResponseEntity<?> res = rest.get("/test/42")
                .accept(MediaType.APPLICATION_JSON)
                .forEntity();

        // then
        assertThat(res.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void acceptMultipleValues() {

        // given
        final FluentRestTemplate rest = FluentRestTemplate.builder()
                .http()
                .host(host())
                .port(port())
                .using(new RestTemplate());

        when(request()
                .withMethod("GET")
                .withPath("/test/42")
                .withHeader("Accept", "application/json", "application/xml", "application/octet-stream"))
                .respond(response()
                        .withStatusCode(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}"));

        // when
        final ResponseEntity<?> res = rest.get("/test/42")
                .accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML)
                .header("Accept", "application/octet-stream")
                .forEntity();

        // then
        assertThat(res.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
