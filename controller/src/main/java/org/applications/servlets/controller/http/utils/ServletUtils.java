package org.applications.servlets.controller.http.utils;

import org.applications.servlets.service.api.dto.AllStatDto;
import org.applications.servlets.service.api.interfaces.IStatService;
import org.applications.servlets.service.factory.ServiceFactorySingleton;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class ServletUtils {

    private static IStatService statService = ServiceFactorySingleton.getStatService();

    public static void makeStatForResponse(PrintWriter writer){
        try{
            AllStatDto allStatDto = statService.get();

            writeTo(allStatDto.getArtistStat().getScore(), writer);
            writeBrakeLine(writer);
            writeTo(allStatDto.getGenreStat().getScore(), writer);
            writeBrakeLine(writer);

            for (String line : allStatDto.getAbouts()) {
                writer.write(line + "</br>\n");
            }
        } catch (IOException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }

    }
    public static void writeBrakeLine(Writer writer) throws IOException {
        writer.write("==============================</br>\n");
    }

    public static void writeTo(List<Map.Entry<String, Integer>> data, Writer writer) throws IOException {
        for (Map.Entry<String, Integer> line : data) {
            writer.write(line.getKey() + ": " + line.getValue() + "</br>\n");
        }
    }


}
