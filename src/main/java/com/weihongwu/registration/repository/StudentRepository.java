package com.weihongwu.registration.repository;

import com.weihongwu.registration.dto.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface StudentRepository extends CrudRepository<Student, String> {
    @Query(value = "select student.id, student.name " +
            "from student " +
            "right join course_score on student.id = course_score.student_id " +
            "where course_score.course_id = ?1 " +
            "order by student.name asc", nativeQuery = true)
    List<Student> findAllStudentsInACourse(@Param("course_id") String couse_id);

    @Query(value = "select student.id, student.name " +
            "from student " +
            "where student.id not in " +
            "(select course_score.student_id " +
            "from course_score " +
            "where course_score.score_id = ?1 " +
            "order by student.name asc", nativeQuery = true)
    List<Student> findAllStudentsNotInACourse(@Param("course_id") String course_id);
}
