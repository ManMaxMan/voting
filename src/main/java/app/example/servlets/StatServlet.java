package app.example.servlets;

import app.example.dao.DataAll;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

@WebServlet(urlPatterns = "/stat", loadOnStartup = 1)
public class StatServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        new DataAll().init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        synchronized (DataAll.lock){

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter writer = response.getWriter();

            writeTo(DataAll.artistsScore, writer);
            MyServlet.writeBrakeLine(writer);
            writeTo(DataAll.genresScore, writer);
            MyServlet.writeBrakeLine(writer);
            for (String line : DataAll.aboutList) {
                writer.write(line + "</br>\n");
            }
        }
    }

    private void writeTo(Map<String, Integer> data, Writer writer) throws IOException {
        for (Map.Entry<String, Integer> line : data.entrySet()) {
            writer.write(line.getKey() + ": " + line.getValue() + "</br>\n");
        }
    }
}
