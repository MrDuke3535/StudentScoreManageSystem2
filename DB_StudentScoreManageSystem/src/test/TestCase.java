import com.cqupt.mapper.ChooseMapper;
import com.cqupt.mapper.ClassesMapper;
import com.cqupt.mapper.StudentMapper;
import com.cqupt.mapper.TeacherMapper;
import com.cqupt.pojo.*;
import com.cqupt.service.TeacherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase {
    @Test
    public void testGetPasswordById(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        StudentMapper studentMapper = applicationContext.getBean(StudentMapper.class);
        System.out.println(studentMapper.getPasswordById("2017214712"));
    }

    @Test
    public void testGetPasswordById2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        TeacherMapper teacherMapper = applicationContext.getBean(TeacherMapper.class);
        System.out.println(teacherMapper.getPasswordById("110"));
    }

    @Test
    public void testqueryChooseCourseById(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        ChooseMapper chooseMapper = applicationContext.getBean(ChooseMapper.class);
        List<Choose> chooses = chooseMapper.queryChooseCourseById("2017214712");
        for(Choose choose:chooses){
            System.out.println(choose.toString());
        }
    }

    @Test
    public void testqueryClassById(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        ClassesMapper classesMapper = applicationContext.getBean(ClassesMapper.class);
        List<Classes> classes = classesMapper.queryClassById("110");
        for(Classes c:classes){
            System.out.println(c.toString());
        }

    }

    @Test
    public void testgetChooseNumByClassId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        ChooseMapper chooseMapper = applicationContext.getBean(ChooseMapper.class);
        System.out.println(chooseMapper.getChooseNumByClassId("c00001"));
    }

    @Test
    public void testgetSeeStudentByClassId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        ChooseMapper chooseMapper = applicationContext.getBean(ChooseMapper.class);
        List<SeeStudent> seeStudents = chooseMapper.getSeeStudentByClassId("c00001");
        for(SeeStudent seeStudent:seeStudents){
            System.out.println(seeStudent);
        }
    }

    @Autowired
    private TeacherService teacherService = null;

    @Test
    public void testgetSeeStudent(){
        List<SeeStudent> seeStudents = teacherService.getSeeStudent("c00001");
        for(SeeStudent seeStudent:seeStudents){
            System.out.println(seeStudent.toString());
        }
    }

    @Test
    public void testupdateScore(){
        UpdateScore updateScore = new UpdateScore();
        updateScore.setStuId("2017214714");
        updateScore.setClassId("c00001");
        updateScore.setScore(77);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        ChooseMapper chooseMapper = applicationContext.getBean(ChooseMapper.class);
        chooseMapper.updateScore(updateScore);
    }

    @Test
    public void test(){
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        ChooseMapper chooseMapper = applicationContext.getBean(ChooseMapper.class);
        Map map = new HashMap();
        map.put("classId","c00001");
        map.put("keyWord","李");
        List<SeeStudent> seeStudents = chooseMapper.getStudentByKeyWord(map);
        for(SeeStudent seeStudent:seeStudents ){
            System.out.println(seeStudent);
        }
    }

    @Test
    public void testUpdate(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        StudentMapper studentMapper = applicationContext.getBean(StudentMapper.class);
        Student s = new Student();
        s.setId("2017214712");
        s.setName("噜啦噜啦嘞");
        s.setPassword("980912");
        studentMapper.updateStudent(s);
    }

    @Test
    public void testCreateExcel(){

    }

}
