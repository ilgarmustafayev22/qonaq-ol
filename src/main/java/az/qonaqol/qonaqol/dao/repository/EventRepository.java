package az.qonaqol.qonaqol.dao.repository;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {


}
