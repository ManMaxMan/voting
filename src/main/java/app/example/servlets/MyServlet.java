package app.example.servlets;

import app.example.dao.DataAll;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/vote")
public class MyServlet extends HttpServlet {

    private final static String ARTIST_PARAM_NAME = "artist";
    private final static String GENRE_PARAM_NAME = "genre";
    private final static String ABOUT_PARAM_NAME = "about";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        synchronized (DataAll.lock){

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();

            String artist = request.getParameter(ARTIST_PARAM_NAME);
            String[] genres = request.getParameterValues(GENRE_PARAM_NAME);
            String about = request.getParameter(ABOUT_PARAM_NAME);

            if(genres.length<3||genres.length>5||about.equals("")){
                writer.write("Uncorrect!!!");
                return;
            }

            DataAll.artistsScore.compute(artist, (k, v) -> v != null ? v + 1 : 1);

            for (String genre : genres) {
                DataAll.genresScore.compute(genre, (k, v) -> v != null ? v + 1 : 1);
            }

            DataAll.aboutList.add(about);

            List<Map.Entry<String, Integer>> topArtist = getTopWithScore(DataAll.artistsScore);
            List<Map.Entry<String, Integer>> topGenres = getTopWithScore(DataAll.genresScore);

            writeTo(topArtist, writer);
            writeBrakeLine(writer);
            writeTo(topGenres, writer);
            writeBrakeLine(writer);
            for (String line : DataAll.aboutList) {
                writer.write(line + "</br>\n");
            }
        }
    }

    public static void writeBrakeLine(Writer writer) throws IOException {
        writer.write("****************</br>\n");
    }

    private void writeTo(List<Map.Entry<String, Integer>> data, Writer writer) throws IOException {
        for (Map.Entry<String, Integer> line : data) {
            writer.write(line.getKey() + ": " + line.getValue() + "</br>\n");
        }
    }

    private List<String> getTop(Map<String, Integer> data){
        return data.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private List<Map.Entry<String, Integer>> getTopWithScore(Map<String, Integer> data){
        return data.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
    }
}
