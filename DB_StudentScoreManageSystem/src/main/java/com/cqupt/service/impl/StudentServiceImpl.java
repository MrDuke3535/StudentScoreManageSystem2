package com.cqupt.service.impl;

import com.cqupt.mapper.ChooseMapper;
import com.cqupt.pojo.Choose;
import com.cqupt.service.StudentSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentSerice {

    @Autowired
    private ChooseMapper chooseMapper = null;

    @Override
    public List<Choose> getChooses(String id) {
        return chooseMapper.queryChooseCourseById(id);
    }

    @Override
    public void updateFeedback(String stuId,String classID) {
        Choose choose = new Choose();
        choose.setStuId(stuId);
        choose.setClassId(classID);
        chooseMapper.updateFeedback(choose);
    }

}
