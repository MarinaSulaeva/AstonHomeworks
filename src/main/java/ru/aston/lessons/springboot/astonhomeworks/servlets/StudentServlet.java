package ru.aston.lessons.springboot.astonhomeworks.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.StudentDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.StudentNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.service.StudentService;
import ru.aston.lessons.springboot.astonhomeworks.service.impl.StudentServiceImpl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/student/*"})
public class StudentServlet extends HttpServlet {


    private static void setJsonHeader(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    private static String getJson(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader postData = req.getReader();
        String line;
        while ((line = postData.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);

        String responseAnswer = "";
        StudentService studentService = new StudentServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] pathPart = req.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<StudentDTO> students = studentService.getStudents();
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(students);
            } else {
                int idStudent = Integer.parseInt(pathPart[1]);
                StudentDTO studentDTO = studentService.getStudent(idStudent);
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(studentDTO);
            }
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String responseAnswer = "";
        StudentService studentService = new StudentServiceImpl();
        try {
            String[] pathPart = req.getPathInfo().split("/");
            int studentId = Integer.parseInt(pathPart[1]);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            studentService.deleteStudent(studentId);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = "";
        Optional<StudentCreate> studentResponse;
        StudentService studentService = new StudentServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            studentResponse = Optional.ofNullable(objectMapper.readValue(json, StudentCreate.class));
            StudentCreate student = studentResponse.orElseThrow(IllegalArgumentException::new);
            studentService.addStudent(student);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect user Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = "";
        Optional<StudentDTO> studentResponse;
        StudentService studentService = new StudentServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            studentResponse = Optional.ofNullable(objectMapper.readValue(json, StudentDTO.class));
            StudentDTO studentDTO = studentResponse.orElseThrow(IllegalArgumentException::new);
            studentService.updateStudent(studentDTO);
        } catch (StudentNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "student doesn't found";
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect user Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
