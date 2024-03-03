package org.applications.servlets.service.api.interfaces;

import org.applications.servlets.service.api.dto.AllStatDto;
import org.applications.servlets.service.api.dto.StatDto;

public interface IStatService {
    /**
     * Получение всей статистики
     * @return
     */
    AllStatDto get();

    /**
     * Получение статистики по артистам
     * @return
     */
    StatDto getArtistStat();

    /**
     * Получение статистики по жанрам
     * @return
     */
    StatDto getGenreStat();

}
