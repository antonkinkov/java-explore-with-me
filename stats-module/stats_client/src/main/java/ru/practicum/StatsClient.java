package ru.practicum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.dto.HitDto;

import java.util.List;
import java.util.Map;

@Service
public class StatsClient extends BaseClient {

    @Autowired
//    public StatsClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
    public StatsClient(RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:9090"))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addStatistic(HitDto hitDto) {
        return post("/hit", hitDto);
    }

    public ResponseEntity<Object> getStatistic(String startDate, String endDate, List<String> uris, Boolean unique) {
        Map<String, Object> params = Map.of(
                "start", startDate,
                "end", endDate,
                "uris", List.of(String.join(",", uris)),
                "unique", unique
        );
//        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", params);
        return get("stats?start=2020-05-05%2000:00:00&end=2035-05-05%2000:00:00&uris=/events", params);
    }

}
