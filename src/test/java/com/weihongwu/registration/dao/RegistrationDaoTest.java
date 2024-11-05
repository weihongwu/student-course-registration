package com.weihongwu.registration.dao;

import com.weihongwu.registration.dto.Course;
import com.weihongwu.registration.dto.CourseScore;
import com.weihongwu.registration.dto.Student;
import com.weihongwu.registration.exception.CourseNotFoundException;
import com.weihongwu.registration.exception.StudentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
class RegistrationDaoTest {
    @Autowired
    RegistrationDao registrationDao;

    @Test
    public void getStudent() throws StudentNotFoundException, CourseNotFoundException {
        Student student = registrationDao.findStudentById("101");
//        Student student1 = registrationDao.findStudentById("10001");

        Set<CourseScore>  courseScoreSet = student.getScores();

        Student student2 = registrationDao.addStudent("10001", "hello");
        Student student3 = registrationDao.findStudentById("10001");


        Course course = registrationDao.findCourseById("11");
        Student student4 = registrationDao.registryCourse("101", "12");
        Student student5 = registrationDao.registryCourse("102", "11");
        course = registrationDao.findCourseById("11");
        Course course1 = registrationDao.findCourseByName("english");
        List<Student> list = registrationDao.findAllStudentsInACourse("english");
        assertEquals("a", "a");



    }
}
