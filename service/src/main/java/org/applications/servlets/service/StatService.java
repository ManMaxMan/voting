package org.applications.servlets.service;

import org.applications.servlets.service.api.dto.AllStatDto;
import org.applications.servlets.service.api.dto.AllStatDtoBuilder;
import org.applications.servlets.service.api.dto.StatDto;
import org.applications.servlets.service.api.interfaces.IStatService;
import org.applications.servlets.service.api.interfaces.IVoteService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatService implements IStatService {

    private final IVoteService voteService;

    public StatService(IVoteService voteService) {
        this.voteService = voteService;
    }


    @Override
    public AllStatDto get() {
        return AllStatDtoBuilder.builder()
                .setArtistStat(getArtistStat())
                .setGenreStat(getGenreStat())
                .setAbouts(voteService.getAbout())
                .build();
    }

    @Override
    public StatDto getArtistStat() {
        return new StatDto(getTopWithScore(voteService.getArtist()));
    }

    @Override
    public StatDto getGenreStat() {
        return new StatDto(getTopWithScore(voteService.getGenre()));
    }

    private List<Map.Entry<String, Integer>> getTopWithScore(Map<String, Integer> data){
        if(data == null){
            return null;
        }
        return data.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList());
    }
}
