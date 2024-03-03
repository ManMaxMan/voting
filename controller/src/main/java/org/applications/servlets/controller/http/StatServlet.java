package org.applications.servlets.controller.http;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.applications.servlets.controller.http.utils.ServletUtils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/stat")
public class StatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        ServletUtils.makeStatForResponse(writer);

    }


}