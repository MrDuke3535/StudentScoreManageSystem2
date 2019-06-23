package com.cqupt.pojo;

import java.util.ArrayList;
import java.util.List;

public class AdminStudent {
    private String id;
    private String name;
    private List<AdminClass2> adminClass2s;

    public AdminStudent(){
        adminClass2s = new ArrayList<>();
    }

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

    public List<AdminClass2> getAdminClass2s() {
        return adminClass2s;
    }

    public void setAdminClass2s(List<AdminClass2> adminClass2s) {
        this.adminClass2s = adminClass2s;
    }

    @Override
    public String toString() {
        return "AdminStudent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", adminClass2s=" + adminClass2s +
                '}';
    }
}
