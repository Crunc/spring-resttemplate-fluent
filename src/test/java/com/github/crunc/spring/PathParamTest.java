package com.github.crunc.spring;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class PathParamTest extends MockServerTest {

    @Test
    public void singlePathParam() {

        // given
        final FluentRestTemplate rest = FluentRestTemplate.builder()
                .http()
                .host(host())
                .port(port())
                .using(new RestTemplate());

        when(request()
                .withMethod("POST")
                .withPath("/test/42"))
                .respond(response()
                        .withStatusCode(200));

        // when
        final ResponseEntity<?> res = rest.post("/test/{id}")
                .param("id", 42)
                .forEntity();

        // then
        assertThat(res.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
