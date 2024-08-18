package com.github.saiyamn.learningnavigator.service.student;

import com.github.saiyamn.learningnavigator.dto.student.StudentResponseBody;
import com.github.saiyamn.learningnavigator.entity.Student;

import java.util.List;

public interface IStudentService {

    /*
     * GET /api/v1/users   -- get all students
     * POST /api/v1/users  -- Add a user
     * GET /api/v1/users/{userId}   --- Get a user with userId
     *
     *
     * */

    public StudentResponseBody addStudent(String userName);

    public StudentResponseBody getStudent(String studentId);


}
