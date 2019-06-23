package com.cqupt.pojo;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private List<AdminClass> adminClasses;

    public Course(){
        adminClasses = new ArrayList<>();
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

    public List<AdminClass> getAdminClasses() {
        return adminClasses;
    }

    public void setAdminClasses(List<AdminClass> adminClasses) {
        this.adminClasses = adminClasses;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", adminClasses=" + adminClasses +
                '}';
    }
}
