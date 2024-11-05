package com.weihongwu.registration.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CourseScoreKey implements Serializable {
    @Column(name = "student_id")
    String studentId;

    @Column(name = "course_id")
    String courseId;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final CourseScoreKey other = (CourseScoreKey) obj;

        if (this.studentId == null || this.courseId == null || other.studentId == null || other.courseId == null) {
            return false;
        }

        return this.studentId.equals(other.studentId) && this.courseId.equals(other.courseId);
    }

}
