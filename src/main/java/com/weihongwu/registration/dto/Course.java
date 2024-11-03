package com.weihongwu.registration.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Entity
public class Course {
    @Id
    String id;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToMany(mappedBy = "courseSet", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Set<Student> studentSet;
}
