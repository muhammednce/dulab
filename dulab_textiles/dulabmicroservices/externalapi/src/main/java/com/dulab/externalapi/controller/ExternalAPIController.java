package com.dulab.externalapi.controller;

import com.dulab.common.dto.LoginDTO;
import com.dulab.common.dto.UserInfoDTO;
import com.dulab.externalapi.service.IExternalAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external.api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExternalAPIController {

    /** LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ExternalAPIController.class);

    /** service. */
    private final IExternalAPIService service;

    /** tokenStore. */
    private final TokenStore tokenStore;

    /**
     * Constructor to inject dependency.
     * @param serviceIn       {@link IExternalAPIService}
     * @param tokenStoreIn    {@link TokenStore}
     */
    public ExternalAPIController(final IExternalAPIService serviceIn,
                                 final @Qualifier("jdbcTokenStore") TokenStore tokenStoreIn) {
        this.service = serviceIn;
        this.tokenStore = tokenStoreIn;
    }

    /**
     * This method is used to get user details.
     * @param loginDTO Login
     * @return User information
     */
    @PostMapping("/get-user-info")
    public final UserInfoDTO getUserInfo(@RequestBody final LoginDTO loginDTO) {
        return service.getUserInfo(loginDTO);
    }

}
