package ru.practicum.stat_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.stat_service.dto.StatsDto;
import ru.practicum.stat_service.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<EndpointHit, Long> {


    @Query("SELECT  new StatsDto(app,  uri, count(distinct ip) AS hits) " +
            "FROM  EndpointHit" +
            "WHERE  timestamp BETWEEN  :start AND :and" +
            "GROUP BY app, uri ORDER BY EndpointHit DESC")
    List<StatsDto> getStatsWithUniqueIp(@Param("start") LocalDateTime start,
                                        @Param("and") LocalDateTime end);

    @Query("SELECT  new StatsDto(app,  uri, count(distinct ip) AS hits) " +
            "FROM  EndpointHit" +
            "WHERE  timestamp BETWEEN  :start AND :and AND uri IN(:uris)" +
            "GROUP BY app, uri ORDER BY EndpointHit DESC")
    List<StatsDto> getStatsWithUniqueIpAndUrls(@Param("start") LocalDateTime start,
                                               @Param("end") LocalDateTime end,
                                               @Param("uris") List<String> uris);

    @Query("SELECT new StatsDto(app, uri,count(ip) AS hits) " +
            "FROM  EndpointHit" +
            "WHERE timestamp BETWEEN :start AND :and" +
            "GROUP BY app, uri ORDER BY EndpointHit DESC")
    List<StatsDto> getStats(@Param("start") LocalDateTime start,
                            @Param("start") LocalDateTime end);

    @Query("SELECT new StatsDto(app, uri,count(ip) AS hits) " +
            "FROM  EndpointHit" +
            "WHERE timestamp BETWEEN :start AND :and AND uri IN(:uris)" +
            "GROUP BY app, uri ORDER BY EndpointHit DESC")
    List<StatsDto> getStatsWithUrls(@Param("start")LocalDateTime start,
                                    @Param("end") LocalDateTime end,
                                    @Param("uris") List<String> uris);
}
