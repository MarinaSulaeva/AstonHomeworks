package ru.aston.lessons.springboot.astonhomeworks.servlets.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectCreate;
import ru.aston.lessons.springboot.astonhomeworks.dto.SubjectDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.aston.lessons.springboot.astonhomeworks.exceptions.SubjectNotFoundException;
import ru.aston.lessons.springboot.astonhomeworks.service.SubjectService;
import ru.aston.lessons.springboot.astonhomeworks.service.impl.jdbc.SubjectServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/jdbc/subject/*"})
public class SubjectServlet extends HttpServlet {

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
        SubjectService subjectService = new SubjectServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] pathPart = req.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<SubjectDTO> subjectDTOS = subjectService.getAllSubjects();
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(subjectDTOS);
            } else {
                int idSubject = Integer.parseInt(pathPart[1]);
                SubjectDTO subjectDTO = subjectService.getSubject(idSubject);
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(subjectDTO);
            }
        } catch (SubjectNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "Subject doesn't found";
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
        SubjectService subjectService = new SubjectServiceImpl();
        try {
            String[] pathPart = req.getPathInfo().split("/");
            int subjectId = Integer.parseInt(pathPart[1]);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            subjectService.deleteSubject(subjectId);
        } catch (SubjectNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "Subject doesn't found";
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

        SubjectService subjectService = new SubjectServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        String responseAnswer = "";
        Optional<SubjectCreate> subjectResponse;
        try {
            subjectResponse = Optional.ofNullable(objectMapper.readValue(json, SubjectCreate.class));
            SubjectCreate subjectCreate = subjectResponse.orElseThrow(IllegalArgumentException::new);
            subjectService.saveSubject(subjectCreate);
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
        System.out.println("servlet");
        SubjectService subjectService = new SubjectServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        String responseAnswer = "";
        Optional<SubjectDTO> subjectResponse;
        try {
            System.out.println("try section servlet");
            subjectResponse = Optional.ofNullable(objectMapper.readValue(json, SubjectDTO.class));
            SubjectDTO subjectDTO= subjectResponse.orElseThrow(IllegalArgumentException::new);
            System.out.println(subjectDTO);
            subjectService.updateSubject(subjectDTO);
        } catch (SubjectNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = "Subject doesn't found";
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect user Object.";
        }

    }
}
