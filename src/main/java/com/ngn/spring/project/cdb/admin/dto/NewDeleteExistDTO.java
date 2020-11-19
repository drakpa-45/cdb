package com.ngn.spring.project.cdb.admin.dto;

/**
 * ====================================================================
 * Created by Nima Yoezer on 8/26/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public class NewDeleteExistDTO {
    private Object newlyAdded;
    private Object existing;
    private Object deleted;
    private Object edited;

    public Object getNewlyAdded() {
        return newlyAdded;
    }

    public void setNewlyAdded(Object newlyAdded) {
        this.newlyAdded = newlyAdded;
    }

    public Object getExisting() {
        return existing;
    }

    public void setExisting(Object existing) {
        this.existing = existing;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

    public Object getEdited() {
        return edited;
    }

    public void setEdited(Object edited) {
        this.edited = edited;
    }
}
