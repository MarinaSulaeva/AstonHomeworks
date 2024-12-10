package ru.aston.lessons.springboot.astonhomeworks.servlets.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.TeacherDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.TeacherNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.service.TeacherService;
import ru.aston.lessons.springboot.astonhomeworks.service.impl.jdbc.TeacherServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/jdbc/teacher/*"})
public class TeacherServlet extends HttpServlet {


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
        TeacherService teacherService = new TeacherServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] pathPart = req.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<TeacherDTO> teacherDTOS = teacherService.getTeachers();
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(teacherDTOS);
            } else {
                int idTeacher = Integer.parseInt(pathPart[1]);
                TeacherDTO teacherDTO = teacherService.getTeacher(idTeacher);
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(teacherDTO);
            }
        } catch (TeacherNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "Teacher doesn't found";
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
        TeacherService teacherService = new TeacherServiceImpl();
        try {
            String[] pathPart = req.getPathInfo().split("/");
            int teacherId = Integer.parseInt(pathPart[1]);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            teacherService.deleteTeacher(teacherId);
        } catch (TeacherNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "Teacher doesn't found";
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
        Optional<TeacherCreate> teacherResponse;
        TeacherService teacherService = new TeacherServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            teacherResponse = Optional.ofNullable(objectMapper.readValue(json, TeacherCreate.class));
            TeacherCreate teacherCreate = teacherResponse.orElseThrow(IllegalArgumentException::new);
            teacherService.addTeacher(teacherCreate);
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
        Optional<TeacherDTO> teacherResponse;
        TeacherService teacherService = new TeacherServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            teacherResponse = Optional.ofNullable(objectMapper.readValue(json, TeacherDTO.class));
            TeacherDTO teacherDTO= teacherResponse.orElseThrow(IllegalArgumentException::new);
            teacherService.updateTeacher(teacherDTO);
        } catch (TeacherNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "Teacher doesn't found";
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect user Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
