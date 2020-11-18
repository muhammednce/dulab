package com.dulab.externalapi.service;

import com.dulab.common.dto.LoginDTO;
import com.dulab.common.dto.UserInfoDTO;

public interface IExternalAPIService {

    /**
     * This method is used to validate user login.
     * @param username unique user name
     * @param password user password
     * @param clientIpAddress user password
     */
    void login(String username, String password, String clientIpAddress);


    /**
     * This method is used to get user details.
     * @param loginDTO Login
     * @return User information
     */
    UserInfoDTO getUserInfo(LoginDTO loginDTO);
}
