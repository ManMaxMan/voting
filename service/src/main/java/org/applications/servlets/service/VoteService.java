package org.applications.servlets.service;

import org.applications.servlets.dao.api.IVoteDao;
import org.applications.servlets.service.api.interfaces.IVoteService;

import java.util.List;
import java.util.Map;

public class VoteService implements IVoteService {
    private final IVoteDao voteDao;

    public VoteService(IVoteDao voteDao) {
        this.voteDao = voteDao;
    }

    @Override
    public void save(String artist, String[] genres, String about) {

        if(genres == null || genres.length < 3 || genres.length > 5){
            throw new IllegalArgumentException("Необходимо выбрать от 3 до 5 жанров");
        }

        this.voteDao.save(artist, genres, about);
    }


    @Override
    public Map<String, Integer> getArtist() {
        return this.voteDao.getArtist();
    }

    @Override
    public Map<String, Integer> getGenre() {
        return this.voteDao.getGenre();
    }

    @Override
    public List<String> getAbout() {
        return this.voteDao.getAbout();
    }

}
