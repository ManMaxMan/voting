package org.applications.servlets.service.api.dto;

import java.util.ArrayList;
import java.util.List;

public class AllStatDtoBuilder {
    private StatDto artistStat;
    private StatDto genreStat;
    private List<String> abouts = new ArrayList<>();

    private AllStatDtoBuilder() {
    }
    public static AllStatDtoBuilder builder(){
        return new AllStatDtoBuilder();
    }

    public AllStatDtoBuilder setArtistStat(StatDto artistStat) {
        this.artistStat = artistStat;
        return this;
    }

    public AllStatDtoBuilder setGenreStat(StatDto genreStat) {
        this.genreStat = genreStat;
        return this;
    }

    public AllStatDtoBuilder setAbouts(List<String> abouts) {
        this.abouts = abouts;
        return this;
    }

    public AllStatDto build(){
        return new AllStatDto(artistStat, genreStat, abouts);
    }

}
