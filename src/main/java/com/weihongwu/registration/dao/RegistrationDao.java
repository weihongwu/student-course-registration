package com.weihongwu.registration.dao;

import com.weihongwu.registration.dto.Course;
import com.weihongwu.registration.dto.Student;
import com.weihongwu.registration.exception.CourseNotFoundException;
import com.weihongwu.registration.exception.StudentNotFoundException;
import com.weihongwu.registration.repository.CourseRepository;
import com.weihongwu.registration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Repository
public class RegistrationDao {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    @Autowired
    public RegistrationDao(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Student findStudentById(String id) throws StudentNotFoundException {
       return studentRepository.findById(id).orElseThrow(() -> {
           return new StudentNotFoundException();
       });
    }

    public Iterable<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Course findCourseById(String id) throws CourseNotFoundException {
        return courseRepository.findById(id).orElseThrow(()-> {
            return new CourseNotFoundException();});
    }

    public Course findCourseByName(String name) throws CourseNotFoundException {
        return courseRepository.findByName(name).orElseThrow(()-> {
            return new CourseNotFoundException();});
    }


    public Student addStudent(String id, String name) {
        Student student = new Student(id, name, new HashSet<>());
        return studentRepository.save(student);
    }

    public Student registryCourse(String student_id, String course_id) throws CourseNotFoundException, StudentNotFoundException {
        Student student = findStudentById(student_id);
        Course course = findCourseById(course_id);
        student.getCourseSet().add(course);
        return studentRepository.save(student);
    }

    //A new student along with their course registrations.
    public Student addNewStudentAndRegistryCourse(String student_id, String student_name, List<String> course_id_list) throws CourseNotFoundException {
        Student student = new Student(student_id, student_name, new HashSet<>());
        Set<Course> courseSet = student.getCourseSet();
        for(String id : course_id_list) {
            courseSet.add(findCourseById(id));
        }
        return studentRepository.save(student);
    }

    //Delete a student.
    public void deleteStudent(String id) throws StudentNotFoundException {
        Student student = findStudentById(id);
        studentRepository.delete(student);
    }

    //Find all students register for a giving course and return a list sorted by name.
    public List<Student> findAllStudentsInACourse(String courseName) throws CourseNotFoundException {
        Course course = findCourseByName(courseName);
        List<Student> list = new ArrayList<>(course.getStudentSet());
        Collections.sort(list, (a1, a2) -> a1.getName().compareTo(a2.getName()));
        return list;
    }

    //Find all students who don't register for a giving course.
    public List<Student> findAllStudentsNotInACourse(String courseName) throws CourseNotFoundException {
        Course course = findCourseByName(courseName);
        Set<Student> studentSet = course.getStudentSet();


        Iterable<Student> students = findAllStudents();
        List<Student> list = new ArrayList<>();
        students.forEach(list::add);

        return list.stream().filter(a -> doesNotRegister(studentSet, a)).collect(Collectors.toList());
    }

    private boolean doesNotRegister(Set<Student> studentSet, Student student) {
        return !(studentSet.stream().anyMatch(a -> a.getId().equals(student.getId())));
    }
}
