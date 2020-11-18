package com.dulab.common.dto;

import java.util.List;

/**
 * This class is used to get screen object.
 */
public class ScreenDTO {

    /**
     * moduleNo.
     */
    private int moduleNo;

    /**
     * screenNo.
     */
    private int screenNo;

    /**
     * label.
     */
    private String label;

    /**
     * icon.
     */
    private String icon;

    /**
     * routerLink.
     */
    private String routerLink;

    /**
     * items.
     */
    private List<ScreenDTO> items;

    /**
     * subMenuName.
     */
    private String subMenuName;

    /**
     * @return the moduleNo
     */
    public final int getModuleNo() {
        return moduleNo;
    }

    /**
     * @param moduleNoIn the moduleNo to set
     */
    public final void setModuleNo(final int moduleNoIn) {
        moduleNo = moduleNoIn;
    }

    /**
     * @return the screenNo
     */
    public final int getScreenNo() {
        return screenNo;
    }

    /**
     * @param screenNoIn the screenNo to set
     */
    public final void setScreenNo(final int screenNoIn) {
        screenNo = screenNoIn;
    }

    /**
     * @return the label
     */
    public final String getLabel() {
        return label;
    }

    /**
     * @param labelIn the label to set
     */
    public final void setLabel(final String labelIn) {
        label = labelIn;
    }

    /**
     * @return the icon
     */
    public final String getIcon() {
        return icon;
    }

    /**
     * @param iconIn the icon to set
     */
    public final void setIcon(final String iconIn) {
        icon = iconIn;
    }

    /**
     * @return the routerLink
     */
    public final String getRouterLink() {
        return routerLink;
    }

    /**
     * @param routerLinkIn the routerLink to set
     */
    public final void setRouterLink(final String routerLinkIn) {
        routerLink = routerLinkIn;
    }

    /**
     * @return the items
     */
    public final List<ScreenDTO> getItems() {
        return items;
    }

    /**
     * @param itemsIn the items to set
     */
    public final void setItems(final List<ScreenDTO> itemsIn) {
        items = itemsIn;
    }

    /**
     * @return the subMenuName
     */
    public final String getSubMenuName() {
        return subMenuName;
    }

    /**
     * @param subMenuNameIn the subMenuName to set
     */
    public final void setSubMenuName(final String subMenuNameIn) {
        this.subMenuName = subMenuNameIn;
    }
}
