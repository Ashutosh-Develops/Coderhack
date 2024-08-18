package com.github.saiyamn.learningnavigator.service.subject;

import com.github.saiyamn.learningnavigator.dto.enrollment.EnrollmentResponseBody;
import com.github.saiyamn.learningnavigator.dto.subject.SubjectResponseBody;
import com.github.saiyamn.learningnavigator.entity.Enrollment;
import com.github.saiyamn.learningnavigator.entity.Subject;

public interface ISubjectService {

    // API COurses
    /*
     * GET /api/v1/courses   -- get all courses
     * POST /api/v1/courses  -- Add a course
     * GET /api/v1/courses/{courseId}  -- get details of a particular course
     * PUT /api/v1/courses/{courseId}  -- Register a student to the course
     * */


    public SubjectResponseBody addSubject(String subjectName);

    public EnrollmentResponseBody enrollStudentToCourse(String studentId, String courseId);

}
