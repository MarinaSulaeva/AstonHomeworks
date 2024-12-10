package ru.aston.lessons.springboot.astonhomeworks.servlets.hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.StudentNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.service.StudentService;
import ru.aston.lessons.springboot.astonhomeworks.service.impl.hibernate.StudentServiceEntityImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/hibernate/subject_students/*"})
public class SubjectToStudentServlet extends HttpServlet {
    private static void setJsonHeader(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);

        String responseAnswer = "";
        StudentService studentService = new StudentServiceEntityImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] pathPart = req.getPathInfo().split("/");
            int idStudent = Integer.parseInt(pathPart[1]);
                List<SubjectDTO> subjectDTOS = studentService.getStudentsSubjects(idStudent);
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(subjectDTOS);
        } catch (StudentNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "student doesn't found";
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";

        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
