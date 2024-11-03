package com.weihongwu.registration.repository;

import com.weihongwu.registration.dto.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface CourseRepository extends CrudRepository<Course, String> {
    Optional<Course> findByName(String name);
}
