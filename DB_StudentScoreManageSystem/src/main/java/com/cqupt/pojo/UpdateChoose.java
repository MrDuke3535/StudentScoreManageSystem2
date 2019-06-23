package com.cqupt.pojo;

public class UpdateChoose {
    private String oldClassId;
    private String newClassId;
    private String stuId;

    public String getOldClassId() {
        return oldClassId;
    }

    public void setOldClassId(String oldClassId) {
        this.oldClassId = oldClassId;
    }

    public String getNewClassId() {
        return newClassId;
    }

    public void setNewClassId(String newClassId) {
        this.newClassId = newClassId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Override
    public String toString() {
        return "UpdateChoose{" +
                "oldClassId='" + oldClassId + '\'' +
                ", newClassId='" + newClassId + '\'' +
                ", stuId='" + stuId + '\'' +
                '}';
    }
}
