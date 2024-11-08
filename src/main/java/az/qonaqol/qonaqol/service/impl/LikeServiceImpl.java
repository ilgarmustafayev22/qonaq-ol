package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.EventEntity;
import az.qonaqol.qonaqol.dao.entity.LikeEntity;
import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.dao.repository.LikeRepository;
import az.qonaqol.qonaqol.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void toggleLike(long eventId, long userId) {
        likeRepository.findByEvent_IdAndUser_Id(eventId, userId)
                .ifPresentOrElse(
                        likeRepository::delete,
                        () -> likeRepository.save(LikeEntity.builder()
                                .event(EventEntity.builder().id(eventId).build())
                                .user(UserEntity.builder().id(userId).build())
                                .build())
                );
    }

}
