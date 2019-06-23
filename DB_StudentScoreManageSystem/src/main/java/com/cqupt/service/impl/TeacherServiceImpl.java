package com.cqupt.service.impl;

import com.cqupt.mapper.ChooseMapper;
import com.cqupt.mapper.ClassesMapper;
import com.cqupt.pojo.Classes;
import com.cqupt.pojo.SeeStudent;
import com.cqupt.pojo.Student;
import com.cqupt.pojo.UpdateScore;
import com.cqupt.service.TeacherService;
import com.cqupt.utils.CreateExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private ClassesMapper classesMapper = null;
    @Autowired
    private ChooseMapper chooseMapper = null;

    @Override
    public List<Classes> getClasses(String id) {
        List<Classes> classes = classesMapper.queryClassById(id);
        for(Classes c:classes){
            int num = chooseMapper.getChooseNumByClassId(c.getClassId());
            c.setNum(num);
        }
        return classes;
    }

    @Override
    public List<SeeStudent> getSeeStudent(String classId) {
        List<SeeStudent> seeStudents = chooseMapper.getSeeStudentByClassId(classId);
        for(int i=0;i<seeStudents.size();i++){
            int max = -1;
            int k = -1;
            for(int j=0;j<seeStudents.size();j++){
                if(seeStudents.get(j).getScore()>max&&seeStudents.get(j).getRank()==0){
                    max = seeStudents.get(j).getScore();
                    k = j;
                }
            }
            if(k!=-1){
                seeStudents.get(k).setRank(i+1);
            }
        }
        return seeStudents;
    }

    @Override
    public void updateScore(String stuId, String classId, String score) {
        UpdateScore updateScore = new UpdateScore();
        updateScore.setStuId(stuId);
        updateScore.setClassId(classId);
        try {
            updateScore.setScore(Integer.parseInt(score));
        }catch (Exception e){
            updateScore.setScore(-1);
        }
        chooseMapper.updateScore(updateScore);
    }

    @Override
    public Map<String,Integer> getScoreRate(String classId) {
        List<SeeStudent> seeStudents = getSeeStudent(classId);
        Map<String,Integer> rate = new HashMap<>();
        rate.put("A",0);
        rate.put("B",0);
        rate.put("C",0);
        rate.put("D",0);
        for(SeeStudent s:seeStudents){
            if(s.getScore()>=90){
                rate.put("A",rate.get("A")+1);
            }else if(s.getScore()>=80){
                rate.put("B",rate.get("B")+1);
            }else if(s.getScore()>=60){
                rate.put("C",rate.get("C")+1);
            } else if (s.getScore()>-1) {
                rate.put("D",rate.get("D")+1);
            }
        }
        if(rate.get("A")==0&&rate.get("B")==0&&rate.get("C")==0&&rate.get("D")==0){
            rate.put("D",1);
        }
        return rate;
    }

    @Override
    public List<SeeStudent> getStudentByKeyWord(String classId, String keyWord) {
        Map<String,String> map = new HashMap<>();
        map.put("classId",classId);
        map.put("keyWord",keyWord);
        List<SeeStudent> seeStudents = chooseMapper.getStudentByKeyWord(map);
        List<SeeStudent> all = getSeeStudent(classId);
        for(SeeStudent s:seeStudents){
            for(SeeStudent a:all){
                if(a.getId().equals(s.getId())){
                    s.setRank(a.getRank());
                }
            }
        }
        return seeStudents;
    }

    @Override
    public String downloadTable(String classId) {
        String fileDir = "E:\\studentTable\\"+classId+".xls";
        List<SeeStudent> seeStudents = getSeeStudent(classId);
        List<String> sheetName = new ArrayList<>();
        sheetName.add("A");
        sheetName.add("B");
        sheetName.add("C");
        sheetName.add("D");
        sheetName.add("E");
        sheetName.add("F");
        String[] title = {"序号","学号","姓名","科目","成绩","排名"};
        CreateExcelUtil.createExcelXls(fileDir, sheetName, title);
        List<Map<String,String>> userList = new ArrayList<Map<String,String>>();
        int i=1;
        for(SeeStudent seeStudent:seeStudents){
            Map<String,String> map=new HashMap<String,String>();
            map.put("序号", i+"");
            i++;
            map.put("学号", seeStudent.getId());
            map.put("姓名", seeStudent.getName());
            map.put("科目",seeStudent.getCourse());
            map.put("成绩",seeStudent.getScore()+"");
            map.put("排名",seeStudent.getRank()+"");
            userList.add(map);
        }
        CreateExcelUtil.createExcelXls(fileDir, sheetName, title);
        for (int j = 0; j < sheetName.size(); j++) {
            try {
                List<Map<String,String>> value = new ArrayList<>();
                if(userList.size()>0)
                for(int k =0;k<userList.size();k++){
                    Map<String,String> newMap = new HashMap<>();
                    newMap.put(title[j],userList.get(k).get(title[j]));
                    value.add(newMap);
                }
                CreateExcelUtil.writeToExcelXls(fileDir, sheetName.get(j), userList);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return fileDir;
    }


}
