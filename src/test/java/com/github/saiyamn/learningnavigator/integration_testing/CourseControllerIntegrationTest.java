package com.github.saiyamn.learningnavigator.integration_testing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.saiyamn.learningnavigator.dto.enrollment.EnrollToCourseDTO;
import com.github.saiyamn.learningnavigator.dto.enrollment.EnrollmentResponseBody;
import com.github.saiyamn.learningnavigator.dto.subject.AddSubjectDTO;
import com.github.saiyamn.learningnavigator.entity.Enrollment;
import com.github.saiyamn.learningnavigator.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void addSubject() throws Exception {

        AddSubjectDTO addSubjectDTO=new AddSubjectDTO("Physics");   // Request Body
        String API_PATH="/api/v1/subjects";

        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addSubjectDTO)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();


        Subject returnedSubject = objectMapper.readValue(result.getResponse().getContentAsString(),Subject.class);


        Assertions.assertNotNull(returnedSubject);

        Assertions.assertEquals("Physics",returnedSubject.getSubjectName());
    }


    @Test
    public void enrollStudentToACourse() throws Exception {

        String API_PATH="/api/v1/subjects/{courseId}";

        EnrollToCourseDTO enrollToCourseDTO=new EnrollToCourseDTO("1");
        String courseId="52";

        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.put(API_PATH,courseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(enrollToCourseDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        EnrollmentResponseBody enrollmentResponseBody=objectMapper.readValue(result.getResponse().getContentAsString(),EnrollmentResponseBody.class);


        Assertions.assertNotNull(enrollmentResponseBody);

        Assertions.assertEquals(1,enrollmentResponseBody.getEnrollmentStudentInfo().getStudentId());
        Assertions.assertEquals(52,enrollmentResponseBody.getEnrollmentSubjectInfo().getSubjectId());


    }


}
