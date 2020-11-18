package com.dulab.common.dto;

/**
 * This pojo class is used to hold screen list with role access.
 */
public class RoleAccessDTO {

    /** screenID. */
    private int screenID;

    /** moduleID. */
    private int moduleID;

    /** create. */
    private boolean create;

    /** create. */
    private boolean edit;

    /** create. */
    private boolean view;

    /** create. */
    private boolean delete;
    /**
     * Empty constructor.
     */
    public RoleAccessDTO() {
        // Empty constructor
    }
    /**
     * @return the screenID
     */
    public final int getScreenID() {
        return screenID;
    }

    /**
     * @param screenIDIn the screen id to set
     */
    public final void setScreenID(final int screenIDIn) {
        screenID = screenIDIn;
    }

    /**
     * @return the moduleID
     */
    public final int getModuleID() {
        return moduleID;
    }

    /**
     * @param moduleIDIn the moduleID to set
     */
    public final void setModuleID(final int moduleIDIn) {
        moduleID = moduleIDIn;
    }

    /**
     * @return the create
     */
    public final boolean isCreate() {
        return create;
    }
    /**
     * @param createIn the create to set
     */
    public final void setCreate(final boolean createIn) {
        this.create = createIn;
    }

    /**
     * @return the eid
     */
    public final boolean isEdit() {
        return edit;
    }

    /**
     * @param editIn the edit to set
     */
    public final void setEdit(final boolean editIn) {
        this.edit = editIn;
    }


    /**
     * @return the view
     */
    public final boolean isView() {
        return view;
    }

    /**
     * @param viewIn the view to set
     */
    public final void setView(final boolean viewIn) {
        this.view = viewIn;
    }

    /**
     * @return the delete
     */
    public final boolean isDelete() {
        return delete;
    }

    /**
     * @param deleteIn the delete to set
     */
    public final void setDelete(final boolean deleteIn) {
        this.delete = deleteIn;
    }
}
