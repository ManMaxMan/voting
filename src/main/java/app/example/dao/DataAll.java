package app.example.dao;

import java.util.*;

public class DataAll {

    public final static Object lock = new Object();

    public final static Map<String, Integer> artistsScore = new LinkedHashMap<>();
    public final static Map<String, Integer> genresScore = new LinkedHashMap<>();
    public final static List<String> aboutList = new ArrayList<>();

    public void init(){
        artistsScore.put("Григорий Лепс",0);
        artistsScore.put("Дима Билан",0);
        artistsScore.put("Полина Гагарина",0);
        artistsScore.put("Стас Михайлов",0);

        genresScore.put("Эпопея",0);
        genresScore.put("Эпос",0);
        genresScore.put("Роман",0);
        genresScore.put("Повесть",0);
        genresScore.put("Новелла",0);
        genresScore.put("Рассказ",0);
        genresScore.put("Пьеса",0);
        genresScore.put("Очерк",0);
        genresScore.put("Видение",0);
        genresScore.put("Эссе",0);
    }

}
