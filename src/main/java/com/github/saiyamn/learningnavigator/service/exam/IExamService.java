package com.github.saiyamn.learningnavigator.service.exam;

import com.github.saiyamn.learningnavigator.dto.exam.ExamResponseBody;
import com.github.saiyamn.learningnavigator.dto.examRegistration.ExamRegistrationResponseBody;
import com.github.saiyamn.learningnavigator.entity.Exam;
import com.github.saiyamn.learningnavigator.entity.ExamRegistration;
import org.springframework.stereotype.Service;

@Service
public interface IExamService {

    // API Exam
    /*
     * GET /api/v1/exams   -- get all exams
     * POST /api/v1/exams  -- Add an exam
     * GET /api/v1/exams/{examId}  -- get details of a particular course
     * POST /api/v1/exams/{examId}  -- Register a user to the exam
     * */


    public ExamResponseBody addExam(String courseId);

    public ExamRegistrationResponseBody registerUserToExam(String studentId, String examId);
}
