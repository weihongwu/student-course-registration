package com.weihongwu.registration.repository;

import com.weihongwu.registration.dto.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentRepository extends CrudRepository<Student, String> {
}
