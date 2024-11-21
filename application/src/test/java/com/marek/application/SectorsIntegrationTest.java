package com.marek.application;

import com.marek.model.sectors.Sector;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.marek.application.fixtures.TestFixtures.SECTORS_EXPECTED_RESPONSE;
import static org.junit.Assert.assertEquals;

public class SectorsIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldSectors_IfDatabaseInitiated() {
        var response = restTemplate.exchange("/api/sectors", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Sector>>() {
                });

        var responseCode = response.getStatusCode();
        var body = response.getBody();

        assertEquals(HttpStatus.OK, responseCode);
        assertEquals(SECTORS_EXPECTED_RESPONSE, body);
    }
}
