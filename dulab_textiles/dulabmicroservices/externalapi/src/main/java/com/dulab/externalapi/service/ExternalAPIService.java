package com.dulab.externalapi.service;


import com.dulab.common.dao.IDAORepository;
import com.dulab.common.dto.LoginDTO;
import com.dulab.common.dto.RoleAccessDTO;
import com.dulab.common.dto.ScreenDTO;
import com.dulab.common.dto.UserInfoDTO;
import com.dulab.common.service.IQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.dulab.common.constants.QueryNames.USER_LOGIN;
import static com.dulab.common.constants.QueryNames.INSERT_USER_LOGIN_LOG;
import static com.dulab.common.constants.QueryNames.USER_INFO;
import static com.dulab.common.constants.QueryNames.SELECT_SCREENS;
import static com.dulab.common.constants.QueryNames.SELECT_ROLE_ACCESS;
import static com.dulab.common.constants.StringConstants.MODULENO;
import static com.dulab.common.constants.StringConstants.SUB_MENU;
import static com.dulab.common.constants.StringConstants.MODULE_NAME;
import static com.dulab.common.constants.StringConstants.MODULE_ICON;
import static com.dulab.common.constants.StringConstants.SUB_MENU_NAME;
import static com.dulab.common.constants.StringConstants.SCREEN_NAME;
import static com.dulab.common.constants.StringConstants.SCREEN_ICON;
import static com.dulab.common.constants.StringConstants.SCREENNO;
import static com.dulab.common.constants.StringConstants.ROUTER_LINK;


@Service
@Transactional
public class ExternalAPIService implements IExternalAPIService {

    /**
     * LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ExternalAPIService.class);
    /**
     * dao.
     */
    private final IDAORepository dao;
    /**
     * queryService.
     */
    private final IQueryService queryService;

    /**
     * Constructor to inject dependency.
     *
     * @param daoIn          DAORepository
     * @param queryServiceIn QueryService
     */
    public ExternalAPIService(final IDAORepository daoIn, final IQueryService queryServiceIn) {
        this.dao = daoIn;
        this.queryService = queryServiceIn;
    }


    /**
     * This method is used to validate user login.
     *
     * @param username        unique user name
     * @param password        user password
     * @param clientIpAddress user password
     */
    @Override
    public void login(String username, String password, String clientIpAddress) {
        dao.queryForObjectWithRowMapper(queryService.get(USER_LOGIN), UserInfoDTO.class, username, password);
        dao.update(queryService.get(INSERT_USER_LOGIN_LOG), username, new Date(), clientIpAddress, true, null);
    }

    /**
     * This method is used to get user details.
     *
     * @param loginDTO Login
     * @return User information
     */
    @Override
    public UserInfoDTO getUserInfo(LoginDTO loginDTO) {
        try {
            // Load user info
            var userInfoDTO = dao.queryForObjectWithRowMapper(queryService.get(USER_INFO), UserInfoDTO.class,
                    loginDTO.getUserCode());

            // Load Screens
            userInfoDTO.setScreenList(loadScreenDTO(loginDTO.getUserCode()));

            // Load User Roles (Screen access)
            userInfoDTO.setUserRoleAccess(dao.query(queryService.get(SELECT_ROLE_ACCESS), RoleAccessDTO.class,
                    userInfoDTO.getGroupCode()));

            userInfoDTO.setLoginStatus(true);
            return userInfoDTO;
        } catch (Exception e) {
            LOG.error("Login failed", e);
            return new UserInfoDTO("", "", false, "Username/Password incorrect!");
        }
    }

    /**
     * This method is used to load screens.
     *
     * @param userCode User Code
     * @return List of screens
     */
    private List<ScreenDTO> loadScreenDTO(final String userCode) {
        var screenMap = new TreeMap<Integer, ScreenDTO>();
        var subMenuMap = new TreeMap<String, ScreenDTO>();

        var screenMapList = dao.queryForList(queryService.get(SELECT_SCREENS), userCode);
        for (var screen : screenMapList) {
            var moduleNo = Integer.valueOf(screen.get(MODULENO).toString());
            if ((boolean) screen.get(SUB_MENU) && screenMap.containsKey(moduleNo)) {
                loadScreensForNewlyAddedScreenDTO(screenMap, subMenuMap, screen);
            } else if ((boolean) screen.get(SUB_MENU) && !screenMap.containsKey(moduleNo)) {
                loadScreensForExistingScreenDTO(screenMap, subMenuMap, screen);
            } else {
                var item = getScreenDTO(screen, moduleNo, MODULE_NAME, MODULE_ICON);
                screenMap.put(moduleNo, item);
            }
        }
        return new ArrayList<>(screenMap.values());
    }

    /**
     * This method is used to load screens.
     *
     * @param screenMap  User Code
     * @param subMenuMap User Code
     * @param screen     User Code
     */
    private void loadScreensForNewlyAddedScreenDTO(final TreeMap<Integer, ScreenDTO> screenMap,
                                                   final TreeMap<String, ScreenDTO> subMenuMap,
                                                   final Map<String, Object> screen) {
        var moduleNo = Integer.valueOf(screen.get(MODULENO).toString());
        var subMenuName = screen.get(SUB_MENU_NAME);

        var screenDTO = screenMap.get(moduleNo);
        var item = getScreenDTO(screen, moduleNo, SCREEN_NAME, SCREEN_ICON);
        if (subMenuName != null && subMenuMap.containsKey(moduleNo + "_" + subMenuName)) {
            var subItem = subMenuMap.get(moduleNo + "_" + subMenuName);
            subItem.getItems().add(item);
        } else if (subMenuName != null && !subMenuMap.containsKey(moduleNo + "_" + subMenuName)) {
            var subItem = getSubScreenDTO(screen);
            subItem.getItems().add(item);
            screenDTO.getItems().add(subItem);
            subMenuMap.put(moduleNo + "_" + subMenuName.toString(), subItem);
        } else {
            screenDTO.getItems().add(item);
        }

    }

    /**
     * This method is used to load screens.
     *
     * @param screenMap  User Code
     * @param subMenuMap User Code
     * @param screen     User Code
     */
    private void loadScreensForExistingScreenDTO(final TreeMap<Integer, ScreenDTO> screenMap,
                                                 final TreeMap<String, ScreenDTO> subMenuMap,
                                                 final Map<String, Object> screen) {
        var moduleNo = Integer.valueOf(screen.get(MODULENO).toString());
        var subMenuName = screen.get(SUB_MENU_NAME);
        var screenDTO = new ScreenDTO();
        screenDTO.setLabel((String) screen.get(MODULE_NAME));
        screenDTO.setIcon((String) screen.get(MODULE_ICON));
        var item = getScreenDTO(screen, moduleNo, SCREEN_NAME, SCREEN_ICON);

        if (subMenuName != null && subMenuMap.containsKey(moduleNo + "_" + subMenuName)) {
            var subItem = subMenuMap.get(moduleNo + "_" + subMenuName);
            subItem.getItems().add(item);
        } else if (subMenuName != null && !subMenuMap.containsKey(moduleNo + "_" + subMenuName)) {
            var subItem = getSubScreenDTO(screen);
            subItem.getItems().add(item);
            var subItems = new ArrayList<ScreenDTO>();
            subItems.add(subItem);
            screenDTO.setItems(subItems);
            screenMap.put(moduleNo, screenDTO);
            subMenuMap.put(moduleNo + "_" + subMenuName.toString(), subItem);
        } else {
            var items = new ArrayList<ScreenDTO>();
            items.add(item);
            screenDTO.setItems(items);
            screenMap.put(moduleNo, screenDTO);
        }
    }

    /**
     * This method is used to create new screen object.
     *
     * @param screen     Screen details
     * @param moduleNo   Module number
     * @param screenName Screen name
     * @param screenIcon Screen Icon
     * @return New ScreenDTO
     */
    private ScreenDTO getScreenDTO(final Map<String, Object> screen, final Integer moduleNo, final String screenName,
                                   final String screenIcon) {
        var item = new ScreenDTO();
        item.setModuleNo(moduleNo);
        item.setScreenNo(Integer.parseInt(screen.get(SCREENNO).toString()));
        item.setLabel((String) screen.get(screenName));
        item.setIcon((String) screen.get(screenIcon));
        item.setRouterLink((String) screen.get(ROUTER_LINK));
        item.setSubMenuName((String) screen.get(SUB_MENU_NAME));
        return item;
    }

    /**
     * This method is used to create new screen object.
     *
     * @param screen Screen details
     * @return New SubScreenDTO
     */
    private ScreenDTO getSubScreenDTO(final Map<String, Object> screen) {
        var item = new ScreenDTO();
        item.setModuleNo(0);
        item.setScreenNo(0);
        item.setLabel((String) screen.get(SUB_MENU_NAME));
        item.setIcon((String) screen.get(SCREEN_ICON));
        item.setRouterLink(null);
        item.setItems(new ArrayList<>());
        return item;
    }
}
