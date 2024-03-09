package az.qonaqol.qonaqol.dao.repository;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    Optional<EventEntity> findByCategory(EventCategory category);

    List<EventEntity> findByEventDateBetween(LocalDate startDate, LocalDate endDate);

    List<EventEntity> findByEventDateBetweenAndCategory(LocalDate startDate, LocalDate endDate, EventCategory category);

    //List<EventEntity> searchAll(String keyword);
}
