package az.qonaqol.qonaqol.dao.repository;

import az.qonaqol.qonaqol.dao.entity.GiftCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCardEntity, Long> {

}
