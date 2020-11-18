package com.dulab.common.dto;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * This pojo class is used to hold user information.
 */
public class UserInfoDTO {

    /**
     * userCode.
     */
    private String userCode;

    /**
     * userName.
     */
    private String userName;

    /**
     * loginStatus.
     */
    private boolean loginStatus;

    /**
     * message.
     */
    private String message;

    /**
     * mailId.
     */
    private String mailId;


    /**
     * userColumnName.
     */
    private String userColumnName;

    /**
     * screenList.
     */
    private List<ScreenDTO> screenList;

    /**
     * companyUser.
     */
    private boolean companyUser;

    /**
     * groupCode.
     */
    private String groupCode;

    /**
     * userRoleAccess.
     */
    private List<RoleAccessDTO> userRoleAccess;

    /**
     * hierarchyMapping.
     */
    private List<Map<String, Object>> hierarchyMapping;


    /**
     * Empty constructor.
     */
    public UserInfoDTO() {
        // Empty constructor
    }

    /**
     * Constructor using all fields.
     *
     * @param userCodeIn    User code
     * @param userNameIn    User Name
     * @param loginStatusIn Login status
     * @param messageIn     Message
     */
    public UserInfoDTO(final String userCodeIn, final String userNameIn, final boolean loginStatusIn,
                       final String messageIn) {
        this.userCode = userCodeIn;
        this.userName = userNameIn;
        this.loginStatus = loginStatusIn;
        this.message = messageIn;
    }

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
     * @return the userName
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * @param userNameIn the userName to set
     */
    public final void setUserName(final String userNameIn) {
        userName = userNameIn;
    }

    /**
     * @return the loginStatus
     */
    public final boolean isLoginStatus() {
        return loginStatus;
    }

    /**
     * @param loginStatusIn the loginStatus to set
     */
    public final void setLoginStatus(final boolean loginStatusIn) {
        loginStatus = loginStatusIn;
    }

    /**
     * @return the message
     */
    public final String getMessage() {
        return message;
    }

    /**
     * @param messageIn the message to set
     */
    public final void setMessage(final String messageIn) {
        message = messageIn;
    }

    /**
     * @return the mailId
     */
    public final String getMailId() {
        return mailId;
    }

    /**
     * @param mailIdIn the mailId to set
     */
    public final void setMailId(final String mailIdIn) {
        mailId = mailIdIn;
    }

    /**
     * @return the screenList
     */
    public final Collection<ScreenDTO> getScreenList() {
        return screenList;
    }

    /**
     * @param screenListIn the screenList to set
     */
    public final void setScreenList(final List<ScreenDTO> screenListIn) {
        screenList = screenListIn;
    }


    /**
     * @return the userColumnName
     */
    public final String getUserColumnName() {
        return userColumnName;
    }

    /**
     * @param userColumnNameIn the userColumnName to set
     */
    public final void setUserColumnName(final String userColumnNameIn) {
        userColumnName = userColumnNameIn;
    }


    /**
     * @return the isCompanyUser
     */
    public final boolean isCompanyUser() {
        return companyUser;
    }

    /**
     * @param companyUserIn the isCompanyUser to set
     */
    public final void setCompanyUser(final boolean companyUserIn) {
        companyUser = companyUserIn;
    }

    /**
     * @return the groupCode
     */
    public final String getGroupCode() {
        return groupCode;
    }

    /**
     * @param groupCodeIn the groupCode to set
     */
    public final void setGroupCode(final String groupCodeIn) {
        groupCode = groupCodeIn;
    }


    /**
     * @return the userRoleAccess
     */
    public final List<RoleAccessDTO> getUserRoleAccess() {
        return userRoleAccess;
    }

    /**
     * @param userRoleAccessIn the userRoleAccess to set
     */
    public final void setUserRoleAccess(final List<RoleAccessDTO> userRoleAccessIn) {
        this.userRoleAccess = userRoleAccessIn;
    }

    /**
     * @return the getHierarchyMapping
     */
    public final List<Map<String, Object>> getHierarchyMapping() {
        return hierarchyMapping;
    }

    /**
     * @param hierarchyMappingIn the userRoleAccess to set
     */
    public final void setHierarchyMapping(final List<Map<String, Object>> hierarchyMappingIn) {
        this.hierarchyMapping = hierarchyMappingIn;
    }


}
