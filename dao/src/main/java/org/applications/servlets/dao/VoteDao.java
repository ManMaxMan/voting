package org.applications.servlets.dao;


import org.applications.servlets.dao.api.IVoteDao;

import java.util.*;

public class VoteDao implements IVoteDao {

    private final Map<String, Integer> artistsScore;
    private final Map<String, Integer> genresScore;
    private final List<String> aboutList;

    public VoteDao() {
        artistsScore = new HashMap<>();
        genresScore = new HashMap<>();
        aboutList = new ArrayList<>();

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

    @Override
    public synchronized void save(String artist, String[] genres, String about) {
        this.artistsScore.compute(artist, (k, v) -> v != null ? v + 1 : 1);

        for (String genre : genres) {
            this.genresScore.compute(genre, (k, v) -> v != null ? v + 1 : 1);
        }

        this.aboutList.add(about);
    }

    @Override
    public Map<String, Integer> getArtist() {
        return Collections.unmodifiableMap(this.artistsScore);
    }

    @Override
    public Map<String, Integer> getGenre() {
        return Collections.unmodifiableMap(this.genresScore);
    }

    @Override
    public List<String> getAbout() {
        return Collections.unmodifiableList(this.aboutList);
    }
}
