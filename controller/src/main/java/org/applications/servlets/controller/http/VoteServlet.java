package org.applications.servlets.controller.http;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.applications.servlets.controller.http.utils.ServletUtils;
import org.applications.servlets.service.api.interfaces.IVoteService;
import org.applications.servlets.service.factory.ServiceFactorySingleton;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private final static String ARTIST_PARAM_NAME = "artist";
    private final static String GENRE_PARAM_NAME = "genre";
    private final static String ABOUT_PARAM_NAME = "about";

    private IVoteService voteService = ServiceFactorySingleton.getVoteService();


    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String artist = req.getParameter(ARTIST_PARAM_NAME);
        String[] genres = req.getParameterValues(GENRE_PARAM_NAME);
        String about = req.getParameter(ABOUT_PARAM_NAME);

        try{
            this.voteService.save(artist, genres, about);
            ServletUtils.makeStatForResponse(writer);
        } catch (IllegalArgumentException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }



    }

}