package az.qonaqol.qonaqol.dao.repository;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.model.enums.EventCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("SELECT e FROM EventEntity e JOIN FETCH e.photoUrls ORDER BY e.createdAt DESC")
    List<EventEntity> findAllWithPhotoUrls();

    @Query("SELECT e FROM EventEntity e JOIN FETCH e.photoUrls WHERE e.category = :category")
    List<EventEntity> findByCategory(@Param("category") EventCategory category);

    @Query("SELECT e FROM EventEntity e JOIN FETCH e.photoUrls WHERE e.user.id = :userId")
    List<EventEntity> findByUserId(@Param("userId") Long userId);

    //@Query("SELECT e FROM EventEntity e JOIN FETCH e.photoUrls WHERE e.id = :id")
    //Optional<EventEntity> findByIdWithPhotoUrls(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE EventEntity e SET e.viewCount = e.viewCount + 1 WHERE e.id = :id")
    void incrementViewCountById(@Param("id") long id);

    // @Query("SELECT new az.qonaqol.qonaqol.model.dto.EventDetailsDTO(e.id, e.eventName, e.description, e.category, e.language, e.eventPrice, e.eventDate, e.eventStartTime, e.eventEndTime, e.eventLocation, e.contact, e.mainPhotoUrl, e.viewCount, e.photoUrls) " +
    //         "FROM EventEntity e " +
    //         "LEFT JOIN FETCH e.photoUrls " +
    //         "WHERE e.id = :id")
    // Optional<EventDetailsDTO> findByIds(@Param("id") Long id);

    List<EventEntity> findByEventDateBetween(LocalDate startDate, LocalDate endDate);

    List<EventEntity> findByEventDateBetweenAndCategory(LocalDate startDate, LocalDate endDate, EventCategory category);

    @Query("select e from EventEntity e where e.eventName like %?1% or e.description like %?1%")
    List<EventEntity> searchAll(String keyword);

    @Query("SELECT e FROM EventEntity e " +
            "JOIN e.likes l " +
            "JOIN FETCH e.photoUrls " +
            "WHERE l.user.id = :userId")
    List<EventEntity> findLikedEventsByUserId(@Param("userId") Long userId);

}
