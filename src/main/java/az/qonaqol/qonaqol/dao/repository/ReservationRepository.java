package az.qonaqol.qonaqol.dao.repository;

import az.qonaqol.qonaqol.dao.entity.ReservationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query(value = "SELECT r.id FROM reservations r WHERE r.event_id = :eventId", nativeQuery = true)
    List<Long> findByEventId(@Param("eventId") long eventId);

    List<ReservationEntity> findByUsers_Id(Long id);

    @Query(value = "SELECT e.id FROM reservations r " +
            "JOIN events e ON r.event_id = e.id " +
            "JOIN users_reservations ur ON r.id = ur.reservation_id " +
            "WHERE ur.users_id = :userId",
            nativeQuery = true)
    List<Long> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservations r where r.id = r.id", nativeQuery = true)
    void deleteById(long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE * FROM reservations r WHERE r.event.id = :eventId", nativeQuery = true)
    void deleteByEvent_Id(@Param("eventId") long eventId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users_reservations ur " +
            "WHERE ur.reservation_id = :id",
            nativeQuery = true)
    void deleteFromUsersReservationsByReservationId(@Param("id") long id);

}
