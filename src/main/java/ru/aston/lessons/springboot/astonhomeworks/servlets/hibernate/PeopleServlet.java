package ru.aston.lessons.springboot.astonhomeworks.servlets.hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.aston.lessons.springboot.astonhomeworks.dto.PeopleDTO;
import ru.aston.lessons.springboot.astonhomeworks.service.PeopleService;
import ru.aston.lessons.springboot.astonhomeworks.service.impl.hibernate.PeopleServiceEntityImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/hibernate/people/*"})
public class PeopleServlet extends HttpServlet {
    private static void setJsonHeader(HttpServletResponse resp) {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);

        String responseAnswer = "";
        PeopleService peopleService = new PeopleServiceEntityImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String[] pathPart = req.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<PeopleDTO> people = peopleService.getAllPeopleAtSchool();
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(people);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
