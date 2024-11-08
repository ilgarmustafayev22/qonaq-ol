package az.qonaqol.qonaqol.dao.repository;

import az.qonaqol.qonaqol.dao.entity.GiftCardOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCardOrderRepository extends JpaRepository<GiftCardOrderEntity, Long>{

}
