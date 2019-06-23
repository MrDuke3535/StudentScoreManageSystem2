package com.cqupt.service;

import com.cqupt.pojo.Choose;

import java.util.List;

public interface StudentSerice {
    public List<Choose> getChooses(String id);
    public void updateFeedback(String stuId,String classID);
}
