package com.cqupt.mapper;

import com.cqupt.pojo.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    public List<Course> getCourses();
    public void addCourse(Course course);
    public void deleteCourse(String id);
    public void updateCourse(Course course);
    public List<Course> getCourseByKeyWord(String keyWord);
    public String getIdByIdOrName(String course);
}
