package az.qonaqol.qonaqol.dao.repository;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    Optional<EventEntity> findByCategory(EventCategory category);

}
