package com.dulab.externalapi;

import com.dulab.common.dto.LoginDTO;
import com.dulab.common.dto.UserInfoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExternalAPIControllerTest {

    /** restTemplate. */
    @Autowired
    private TestRestTemplate restTemplate;

    /** accessToken. */
    private String accessToken;

    @BeforeEach
    public void init() {
        var url = "/oauth/token?grant_type=password&scope=user_info&username=admin"
                + "&password=0e7517141fb53f21ee439b355b5a1d0a";
        var response = restTemplate.postForEntity(url, null, Map.class);
        accessToken = Objects.requireNonNull(response.getBody()).get("access_token").toString();
    }

    /**
     * Test for login success.
     */
    @Test
    public void testLoginSuccess() {
        var url = "/oauth/token?grant_type=password&scope=user_info&username=admin"
                + "&password=0e7517141fb53f21ee439b355b5a1d0a";
        var response = restTemplate.postForEntity(url, null, Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()).get("access_token"));
    }


    /**
     * Test for login fail.
     */
    @Test
    public void testLoginFail() {
        var url = "/oauth/token?grant_type=password&scope=user_info&username=fgfgfg"
                + "&password=hghghghhh";
        var response = restTemplate.postForEntity(url, null, Map.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Method to test get-user-info.
     */
    @Test
    public void testGetUserInfo() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        var dto = new LoginDTO();
        dto.setUserCode("Admin");
        var entity = new HttpEntity<>(dto, headers);

        var response = restTemplate
                .exchange("/external.api/get-user-info", HttpMethod.POST, entity, UserInfoDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
