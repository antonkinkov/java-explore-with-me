package ru.practicum.repository.Request;

import ru.practicum.dto.request.RequestCountDto;
import ru.practicum.model.request.Request;
import ru.practicum.model.request.RequestStatus;
import ru.practicum.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long>, JpaSpecificationExecutor<Request> {
    List<Request> findAllByRequester(User user);

    List<Request> findAllByEventId(Long id);

    List<Request> findByIdIn(List<Long> requestIds);

    @Query("SELECT new ru.practicum.dto.request.RequestCountDto(r.event.id, COUNT(r.id)) " +
            "FROM Request r " +
            "WHERE r.event.id IN :ids AND r.status = :status " +
            "GROUP BY r.event.id " +
            "ORDER BY COUNT(r.id) DESC")
    List<RequestCountDto> findRequestCountsByEventIdsAndStatus(@Param("ids") List<Long> ids, @Param("status") RequestStatus status);

}
