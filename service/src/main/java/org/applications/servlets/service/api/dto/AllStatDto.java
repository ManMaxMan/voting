package org.applications.servlets.service.api.dto;

import java.util.List;

public class AllStatDto {
    private StatDto artistStat;
    private StatDto genreStat;
    private List<String> abouts;

    public AllStatDto(StatDto artistStat, StatDto genreStat, List<String> abouts) {
        this.artistStat = artistStat;
        this.genreStat = genreStat;
        this.abouts = abouts;
    }

    public StatDto getArtistStat() {
        return artistStat;
    }

    public StatDto getGenreStat() {
        return genreStat;
    }

    public List<String> getAbouts() {
        return abouts;
    }
}
