package az.qonaqol.qonaqol.dao.repository;

import az.qonaqol.qonaqol.dao.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Optional<LikeEntity> findByEvent_IdAndUser_Id(long eventId, long userId);

}
