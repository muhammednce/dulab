package com.dulab.common.dto;
/**
 * This pojo class is used to hold user login credentials.
 * @author elan.
 */
public class LoginDTO {

    /** userCode. */
    private String userCode;

    /** password. */
    private String password;

    /**
     * @return the userCode
     */
    public final String getUserCode() {
        return userCode;
    }

    /**
     * @param userCodeIn the userCode to set
     */
    public final void setUserCode(final String userCodeIn) {
        userCode = userCodeIn;
    }

    /**
     * @return the password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * @param passwordIn the password to set
     */
    public final void setPassword(final String passwordIn) {
        password = passwordIn;
    }
}
