package com.marek.application;


import com.marek.model.sectors.GetPersonSectorsResponseV1;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Map;
import java.util.Objects;

import static com.marek.application.fixtures.TestFixtures.*;
import static org.junit.Assert.assertEquals;

public class UserSectorsIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldSaveUserSectors_IfRequestIsValid() {
        var headers = getSessionHeaders();
        var saveResponse = performSave(headers);

        assertEquals(HttpStatus.OK, saveResponse.getStatusCode());

        var getResponse = restTemplate.exchange("/api/person", HttpMethod.GET,
                new HttpEntity<>(null, headers), new ParameterizedTypeReference<GetPersonSectorsResponseV1>() {
                });

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(VALID_SAVE_RESPONSE.hasAgreedToTerms(), Objects.requireNonNull(getResponse.getBody()).hasAgreedToTerms());
        assertEquals(VALID_SAVE_RESPONSE.name(), Objects.requireNonNull(getResponse.getBody()).name());
        assertEquals(VALID_SAVE_RESPONSE.sectors(), Objects.requireNonNull(getResponse.getBody()).sectors());
    }

    @Test
    public void shouldUpdateUserSectors_IfRequestIsValid() {
        var headers = getSessionHeaders();
        var saveResponse = performSave(headers);

        assertEquals(HttpStatus.OK, saveResponse.getStatusCode());

        restTemplate.exchange("/api/person", HttpMethod.PUT,
                new HttpEntity<>(VALID_UPDATE_REQUEST, headers), String.class);

        var getResponse = restTemplate.exchange("/api/person", HttpMethod.GET,
                new HttpEntity<>(null, headers), new ParameterizedTypeReference<GetPersonSectorsResponseV1>() {
                });

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(VALID_UPDATE_RESPONSE.hasAgreedToTerms(), Objects.requireNonNull(getResponse.getBody()).hasAgreedToTerms());
        assertEquals(VALID_UPDATE_RESPONSE.name(), Objects.requireNonNull(getResponse.getBody()).name());
        assertEquals(VALID_UPDATE_RESPONSE.sectors(), Objects.requireNonNull(getResponse.getBody()).sectors());
    }

    @Test
    public void shouldReturnBadRequestWithErrors_IfRequestIsInvalid() {
        var response = restTemplate.exchange("/api/person", HttpMethod.POST,
                new HttpEntity<>(INVALID_SAVE_REQUEST), Map.class);

        var responseCode = response.getStatusCode();
        var body = response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseCode);
        assertEquals(INVALID_SAVE_RESPONSE, body);
    }

    private ResponseEntity<String> performSave(HttpHeaders cookie) {
        return restTemplate.exchange("/api/person", HttpMethod.POST,
                new HttpEntity<>(VALID_SAVE_REQUEST, cookie), String.class);
    }

    private HttpHeaders getSessionHeaders() {
        var response = restTemplate.exchange("/api/session", HttpMethod.POST, null,
                String.class);

        var sessionCookie = Objects.requireNonNull(response.getHeaders().get("Set-Cookie")).getFirst();
        var headers = new HttpHeaders();
        headers.add("Cookie", sessionCookie);

        return headers;
    }

}
