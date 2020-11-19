package com.ngn.spring.project.cdb.contractor.registration.dto;

import com.ngn.spring.project.cdb.contractor.registration.model.ConCategory;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 10/6/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class CategoryDTO {
    private String id;
    private String name;
    private String code;
    private String aClass;
    private List<ConCategory> categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getaClass() {
        return aClass;
    }

    public void setaClass(String aClass) {
        this.aClass = aClass;
    }

    public List<ConCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ConCategory> categories) {
        this.categories = categories;
    }
}
