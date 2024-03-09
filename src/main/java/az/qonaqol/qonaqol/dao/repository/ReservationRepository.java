package az.qonaqol.qonaqol.dao.repository;

import az.qonaqol.qonaqol.dao.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

}
