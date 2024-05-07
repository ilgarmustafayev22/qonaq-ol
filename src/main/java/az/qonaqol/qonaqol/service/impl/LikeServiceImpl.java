package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.dao.entity.LikeEntity;
import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.dao.repository.EventRepository;
import az.qonaqol.qonaqol.dao.repository.LikeRepository;
import az.qonaqol.qonaqol.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void toggleLike(long eventId, long userId) {
        Optional<LikeEntity> likeEntity = likeRepository.findByEvent_IdAndUser_Id(eventId, userId);
        if (likeEntity.isPresent()) {
            likeRepository.delete(likeEntity.get());
        } else {
            likeRepository.save(LikeEntity.builder()
                    .event(EventEntity.builder().id(eventId).build())
                    .user(UserEntity.builder().id(userId).build())
                    .build());
        }
    }

}
